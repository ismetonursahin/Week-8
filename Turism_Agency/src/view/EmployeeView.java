package view;

import business.HotelManager;
import business.PensionManager;
import dao.HotelDao;
import dao.UserDao;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JTable table1;
    private JTable table2;
    private JPanel pnl_pensions;
    private JPanel pnl_seasons;
    private JScrollPane scrl_pension;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JLabel lbl_welcome;
    private User user = new User();
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private DefaultTableModel tmdl_hotels = new DefaultTableModel();
    private DefaultTableModel tmdl_pensions = new DefaultTableModel();
    private JPopupMenu hotels_menu;
    private HotelDao hotelDao;

    public EmployeeView(User user) {
        this.user = user;
        this.hotelDao = new HotelDao();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        add(container);
        guiInitilaze(850, 600);
        this.setTitle("Turism Agenty - Employee Screen");

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getName());

        loadLogout();

        loadHotelTable();
        loadHotelComponent();

        loadPensionTable(null);


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

    public void loadLogout() {

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });
    }
}
