package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import dao.HotelDao;
import entity.Hotel;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotels;
    private JScrollPane scrl_hotels;
    private JTable tbl_hotels;
    private JButton btn_add;
    private JPanel pnl_rooms;
    private JPanel pnl_reservation;
    private JTable tbl_room;
    private JTable table2;
    private JPanel pnl_pensions;
    private JPanel pnl_seasons;
    private JScrollPane scrl_pension;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JLabel lbl_welcome;
    private JButton btn_room_clear;
    private JTextField fld_adult_num;
    private JTextField fld_child_num;
    private JButton btn_room_add;
    private JButton btn_room_search;
    private JScrollPane scrl_room;
    private User user = new User();
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private DefaultTableModel tmdl_hotels = new DefaultTableModel();
    private DefaultTableModel tmdl_pensions = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private JPopupMenu hotels_menu;
    private JPopupMenu room_menu;
    private HotelDao hotelDao;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_finish_date;
    private JComboBox cmb_hotel_city;
    private JComboBox cmb_hotel_name;
    private Object[] col_room;

    public EmployeeView(User user) {
        this.user = user;
        this.hotelDao = new HotelDao();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        add(container);
        guiInitilaze(1200, 600);
        this.setTitle("Tourism Agency - Employee Screen");

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getName());

        loadLogout();

        loadHotelTable();
        loadHotelComponent();

        loadPensionTable(null);
        loadSeasonTable(null);

        loadRoomComponent();
        loadRoomTable(null);

        loadRoomFilter();

    }


    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Otel Adı", "Şehir", "Bölge", "Adres", "E-posta", "Telefon", "Yıldız", "Otopark", "WiFi", "Havuz", "Fitness", "Konsiyerj", "SPA", "Oda Servisi"};
        ArrayList<Object[]> hotelList = hotelManager.getForTable(col_hotel.length);
        this.createTable(this.tmdl_hotels, this.tbl_hotels, col_hotel, hotelList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotels);
        this.hotels_menu = new JPopupMenu();
        this.hotels_menu.add("Pansiyon Ekle").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(tbl_hotels, 0);
            PensionView pensionView = new PensionView(this.hotelManager.getById(selectModelId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null);
                }
            });
        });

        this.hotels_menu.add("Dönem Ekle").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(tbl_hotels, 0);
            SeasonView seasonView = new SeasonView(this.hotelManager.getById(selectModelId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                }
            });
        });

        btn_add.addActionListener(new ActionListener() {   // hotel ekleme butonu
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelView hotelView = new HotelView(new Hotel());
                hotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            }
        });
        this.tbl_hotels.setComponentPopupMenu(hotels_menu);
    }

    public void loadPensionTable(ArrayList<Object[]> pensionList) {
        Object[] col_pension = {"ID", "Otel ID", "Pansiyon Tipi"};
        if (pensionList == null) {
            pensionList = pensionManager.getForTable(col_pension.length, pensionManager.findAll());

        }
        this.createTable(this.tmdl_pensions, this.tbl_pension, col_pension, pensionList);
    }


    public void loadSeasonTable(ArrayList<Object[]> seasonlist) {
        Object[] col_season = {"ID", "Otel ID", "Başlangıç Tarihi", "Bitiş Tarihi"};
        if (seasonlist == null) {
            seasonlist = seasonManager.getForTable(col_season.length, seasonManager.findAll());

        }
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonlist);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Otel ID", "Pansiyon Tipi", "Oda Tipi", "Sezon", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Sayısı", "Metre Kare", "TV", "Minibar", "Konsol", "Kasa", "Klima"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_room.length, roomManager.findAll());

        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    public void loadRoomComponent() {
        btn_room_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView();
                loadRoomTable(null);
            }
        });

        btn_room_search.addActionListener(e -> {
            ComboItem selectedNameId = (ComboItem) this.cmb_hotel_name.getSelectedItem();
            int HotelNameId = 0;
            if (selectedNameId != null) {
                HotelNameId = selectedNameId.getKey();
            }
            ComboItem selectCity = (ComboItem) this.cmb_hotel_city.getSelectedItem();
            if (selectCity != null) {
                String city = selectCity.getValue().toString();

                ArrayList<Room> roomList = this.roomManager.searchForRoom(
                        HotelNameId,
                        city,
                        fld_strt_date.getText(),
                        fld_finish_date.getText(),
                        fld_adult_num.getText(),
                        fld_child_num.getText()
                );
                System.out.println(roomList);

            ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room.length, roomList);
            loadRoomTable(roomRow);

            }
        });

        tableRowSelect(this.tbl_room);
        this.room_menu = new JPopupMenu();
        this.room_menu.add("Rezervasyon Yap").addActionListener(e -> {

        });
        this.tbl_room.setComponentPopupMenu(room_menu);


        btn_room_clear.addActionListener(e -> {
                loadRoomFilter();
                loadRoomTable(null);
        });
    }

    public void loadRoomFilter() {

        loadRoomHotelNameFilter();
        loadRoomCityFilter();
    }

    public void loadRoomHotelNameFilter() {
        //hotel cmb
        cmb_hotel_name.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()) {
            this.cmb_hotel_name.addItem(new ComboItem(obj.getId(), obj.getName()));
        }
        this.cmb_hotel_name.setSelectedItem(null);
    }

    public void loadRoomCityFilter() {
        // city cmb
        cmb_hotel_city.removeAllItems();
        for (Hotel obj : this.hotelManager.findAll()) {
            this.cmb_hotel_city.addItem(new ComboItem(obj.getId(), obj.getCity()));
        }
        this.cmb_hotel_city.setSelectedItem(null);
    }


    public void loadLogout() {

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });
    }


//    private void createUIComponents() throws ParseException {
//       this.fld_strt_date= new JFormattedTextField(new MaskFormatter("##/##/####"));
//       this.fld_finish_date= new JFormattedTextField(new MaskFormatter("##/##/####"));
//
//    }
}
