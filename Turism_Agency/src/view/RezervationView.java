package view;

import business.HotelManager;
import business.RezervationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Rezervation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RezervationView extends Layout {
    private JPanel container;
    private JPanel pnl_room_info;
    private JTextField fld_check_in;
    private JTextField fld_child_number;
    private JTextField fld_check_out;
    private JTextField fld_adult_number;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_total_price;
    private JTextField fld_rez_name;
    private JTextField fld_rez_tc;
    private JTextField fld_rez_mail;
    private JTextField fld_rez_phone;
    private JButton btn_rez_save;
    private JRadioButton rd_park_rez;
    private JRadioButton rd_wifi_rez;
    private JRadioButton rd_pool_rez;
    private JRadioButton rd_spa_rez;
    private JRadioButton rd_concierge_rez;
    private JRadioButton rd_service_rez;
    private JRadioButton rd_fitness_rez;
    private JTextField fld_day;
    private JRadioButton rd_tv_rez;
    private JRadioButton rd_safe_rez;
    private JRadioButton re_minibar_rez;
    private JRadioButton rd_condi_rez;
    private JRadioButton rd_consol_rez;
    private JLabel lbl_room_type;
    private JLabel lbl_bed_num;
    private JLabel lbl_mt;
    private JLabel lbl_pension;
    private JLabel lbl_city_rez;
    private JLabel lbl_region_rez;
    private JLabel lbl_adress_rez;
    private JLabel lbl_hotel_name;
    private JLabel lbl_star;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private RezervationManager rezervationManager;
    private Hotel hotel;
    private Rezervation rezervation;
    private Room room;


    public RezervationView(Rezervation rezervation ,Room room, String start_date, String finish_date, String adult_num, String child_num) {
        this.room = room;
        this.rezervation = new Rezervation();
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.rezervationManager = new RezervationManager();
        add(container);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Turism Agency - Rezervation");
        this.guiInitilaze(900, 800);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));


        this.lbl_hotel_name.setText("Otel : " + room.getHotel().getName());
        this.lbl_star.setText("Yıldız : " + room.getHotel().getStar());
        this.lbl_city_rez.setText("Şehir : " + room.getHotel().getCity());
        this.lbl_region_rez.setText("Bölge : " + room.getHotel().getRegion());
        this.lbl_adress_rez.setText("Adres : " + room.getHotel().getAdress());
        this.rd_park_rez.setSelected(room.getHotel().isPark());
        this.rd_wifi_rez.setSelected(room.getHotel().isWifi());
        this.rd_pool_rez.setSelected(room.getHotel().isPool());
        this.rd_fitness_rez.setSelected(room.getHotel().isFitness());
        this.rd_concierge_rez.setSelected(room.getHotel().isConcierge());
        this.rd_spa_rez.setSelected(room.getHotel().isSpa());
        this.rd_service_rez.setSelected(room.getHotel().isRoom_service());
        this.lbl_room_type.setText(room.getRoom_type().getName());
        this.lbl_bed_num.setText(String.valueOf("Yatak Sayısı : " +room.getBed()));
        this.lbl_mt.setText(String.valueOf("Metrekare : " + room.getMeter()));
        this.lbl_pension.setText(room.getPension_type_id().getName());
        this.rd_tv_rez.setSelected(room.isTv());
        this.re_minibar_rez.setSelected(room.isMinibar());
        this.rd_consol_rez.setSelected(room.isConsol());
        this.rd_safe_rez.setSelected(room.isSafe());
        this.rd_condi_rez.setSelected(room.isCondition());
        this.fld_check_in.setText(start_date.toString());
        this.fld_check_out.setText(finish_date.toString());
        this.fld_adult_number.setText(String.valueOf(adult_num));
        this.fld_child_number.setText(String.valueOf(child_num));
        this.fld_adult_price.setText(String.valueOf(room.getAdult_price()));
        this.fld_child_price.setText(String.valueOf(room.getChild_price()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate firstDate = LocalDate.parse(start_date,formatter);
        LocalDate lastDate = LocalDate.parse(finish_date,formatter);

        int dateNum = (int) ChronoUnit.DAYS.between(firstDate,lastDate);

        this.fld_day.setText(String.valueOf(dateNum));


        int adultPrice = room.getAdult_price();
        int childPrice = room.getChild_price();
        int totalPrice = ((adultPrice * Integer.parseInt(adult_num) * dateNum)+(childPrice * Integer.parseInt(child_num) * dateNum));
        this.fld_total_price.setText(String.valueOf(totalPrice));

        btn_rez_save.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_rez_name,
                    this.fld_rez_tc,
                    this.fld_rez_mail,
                    this.fld_rez_phone
            };
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                boolean result ;
                Rezervation rez = new Rezervation();
                rez.setRoom_id(this.room.getId());
                rez.setCheck_in(LocalDate.parse(start_date,DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                rez.setCheck_out(LocalDate.parse(finish_date,DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                rez.setTotal_price(Integer.parseInt(this.fld_total_price.getText()));
                int guestNum = Integer.parseInt(this.fld_adult_number.getText()) + Integer.parseInt(this.fld_child_number.getText());
                rez.setGuest_num((guestNum));
                rez.setGuest_name(this.fld_rez_name.getText());
                rez.setGuest_tc(this.fld_rez_tc.getText());
                rez.setGuest_mail(this.fld_rez_mail.getText());
                rez.setGuest_phone(this.fld_rez_phone.getText());
                rez.setDay(Integer.parseInt(this.fld_day.getText()));


                // Save or update the reservation based on whether it already exists
                if (rez.getId() != 0){
                    result = this.rezervationManager.update(rez);
                } else {
                    result = this.rezervationManager.save(rez);
                }
                // Show appropriate message based on the result of the operation
                if (result){
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }

}
