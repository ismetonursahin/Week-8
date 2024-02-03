package view;

import business.*;
import core.ComboItem;
import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.Rezervation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
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
    private JTable tbl_rez;
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
    private RezervationManager rezervationManager;
    private DefaultTableModel tmdl_hotels = new DefaultTableModel();
    private DefaultTableModel tmdl_pensions = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_rez = new DefaultTableModel();
    private JPopupMenu hotels_menu;
    private JPopupMenu room_menu;
    private JPopupMenu rezervation_menu;
    private HotelDao hotelDao;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_finish_date;
    private JComboBox cmb_hotel_city;
    private JComboBox cmb_hotel_name;
    private JScrollPane scrl_rez;
    private Object[] col_room;
    private Object[] col_rezervation;


    public EmployeeView(User user) {
        this.user = user;
        this.hotelDao = new HotelDao();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.rezervationManager = new RezervationManager();
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

        loadRezervationComponent();
        loadRezervationTable(null);

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

    public void loadRezervationTable(ArrayList<Object[]> rezervationList) {
        col_rezervation = new Object[]{"ID", "Oda ID", "İsim Soyisim", "T.C.", "E-Posta", "Telefon", "Giriş Tarihi", "Çıkış Tarihi", "Gün", "Toplam Tutar", "Misafir Sayısı"};
        if (rezervationList == null) {
            rezervationList = rezervationManager.getForTable(col_rezervation.length, rezervationManager.findAll());

        }
        this.createTable(this.tmdl_rez, this.tbl_rez, col_rezervation, rezervationList);
    }

    public void loadRezervationComponent() {

        tableRowSelect(this.tbl_rez);
        this.rezervation_menu = new JPopupMenu();
        this.rezervation_menu.add("Güncelle").addActionListener(e -> {

            int selectedRezId = this.getTableSelectedRow(tbl_rez, 0);
            Rezervation selectReservation = this.rezervationManager.getById(selectedRezId);

            int selectedRoomId = selectReservation.getRoom_id();
            Room selectedRoom = this.roomManager.getById(selectedRoomId);

            String inDate = (this.fld_strt_date.getText());
            String outDate = (this.fld_finish_date.getText());

            String adultNum = String.valueOf(this.rezervationManager.getById(selectedRezId).getAdult_num()) ;
            String childNum = String.valueOf(this.rezervationManager.getById(selectedRezId).getChild_num());

            RezervationView rezervationView = new RezervationView(selectReservation, selectedRoom, inDate, outDate, adultNum, childNum);
            rezervationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRezervationTable(null);
                    loadRoomTable(null);
                }
            });

            // Değerlendirme Formu - 22

        });


        this.rezervation_menu.add("Sil").addActionListener(e -> {
            // Deleting a reservation
            if (Helper.confirm("sure")) {
                int selectedReservationId = this.getTableSelectedRow(tbl_rez, 0);
                Rezervation selectedRezervation = this.rezervationManager.getById(selectedReservationId);
                int selectRoomId = selectedRezervation.getRoom_id();

                if (this.rezervationManager.delete(selectedReservationId)) {
                    // Deleting the reservation and updating tables
                    // Değerlendirme Formu - 24
                    Helper.showMsg("done");
                    // Değerlendirme Formu - 23
                    try {
                        this.roomManager.increaseRoomStock(selectRoomId);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    loadRezervationTable(null);
                    loadRoomTable(null);
                } else {
                    // Değerlendirme Formu - 25
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_rez.setComponentPopupMenu(rezervation_menu);
    }

    public void loadRoomComponent() {
        this.fld_child_num.setText("0");
        this.fld_adult_num.setText("0");
        btn_room_add.addActionListener(e -> {
            RoomView roomView = new RoomView();
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
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
            String adultNumText = this.fld_adult_num.getText();
            String childNumText = this.fld_child_num.getText();

            if (!isNumeric(adultNumText)) {
                // Eğer adultNumText bir sayı değilse, kullanıcıya bir hata mesajı göster
                Helper.showMsg(adultNumText + " Geçerli bir sayı değil.");
                return; // Fonksiyonu burada sonlandır, çünkü devam etmek anlamsız olacaktır.
            }
            if (!isNumeric(childNumText)) {
                Helper.showMsg(childNumText + " Geçerli bir sayı değil.");
                return;
            }

            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
            RezervationView rezervationView = new RezervationView(
                    null,
                    this.roomManager.getById(selectedRoomId),
                    this.fld_strt_date.getText(),
                    this.fld_finish_date.getText(),
                    this.fld_adult_num.getText(),
                    this.fld_child_num.getText()
            );
            rezervationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        roomManager.decreaseRoomStock(selectedRoomId);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    loadRezervationTable(null);
                    loadRoomTable(null);
                }
            });
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

    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("05/07/2024");
        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_finish_date.setText("10/07/2024");
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
