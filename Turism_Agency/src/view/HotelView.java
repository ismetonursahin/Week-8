package view;

import business.HotelManager;
import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_adress;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;

    private JRadioButton rd_spa;
    private JRadioButton rd_pool;
    private JRadioButton rd_park;
    private JRadioButton rd_wifi;
    private JRadioButton rd_ftns;
    private JRadioButton rd_concierge;
    private JRadioButton rd_service;
    private JButton btn_hotel_save;
    private HotelManager hotelManager;
    private Hotel hotel;

    public HotelView(Hotel hotel) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(600, 500);


        if (hotel.getId() != 0) {
            this.fld_hotel_name.setText(hotel.getName());
            this.fld_hotel_city.setText(hotel.getCity());
            this.fld_hotel_region.setText(hotel.getRegion());
            this.fld_hotel_adress.setText(hotel.getAdress());
            this.fld_hotel_mail.setText(hotel.getMail());
            this.fld_hotel_phone.setText(hotel.getPhone());
            this.fld_hotel_star.setColumns(hotel.getStar());
            this.rd_park.setSelected(hotel.isPark());
            this.rd_wifi.setSelected(hotel.isWifi());
            this.rd_pool.setSelected(hotel.isPool());
            this.rd_ftns.setSelected(hotel.isFitness());
            this.rd_concierge.setSelected(hotel.isConcierge());
            this.rd_spa.setSelected(hotel.isSpa());
            this.rd_service.setSelected(hotel.isRoom_service());

        }

        btn_hotel_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{
                    this.fld_hotel_name,
                    this.fld_hotel_city,
                    this.fld_hotel_region,
                    this.fld_hotel_adress,
                    this.fld_hotel_mail,
                    this.fld_hotel_phone,
                    this.fld_hotel_star
            })) {
                Helper.showMsg("fill"); // textField kısmında boş alan kalmaması gereken yerlerde gelen uyarı
            } else {

                this.hotel.setName(fld_hotel_name.getText());
                this.hotel.setCity(fld_hotel_city.getText());
                this.hotel.setRegion(fld_hotel_region.getText());
                this.hotel.setAdress(fld_hotel_adress.getText());
                this.hotel.setMail(fld_hotel_mail.getText());
                this.hotel.setPhone(fld_hotel_phone.getText());
                this.hotel.setStar(Integer.parseInt(this.fld_hotel_star.getText()));
                this.hotel.setPark(rd_park.isSelected());
                this.hotel.setWifi(rd_wifi.isSelected());
                this.hotel.setPool(rd_pool.isSelected());
                this.hotel.setFitness(rd_ftns.isSelected());
                this.hotel.setConcierge(rd_concierge.isSelected());
                this.hotel.setSpa(rd_spa.isSelected());
                this.hotel.setRoom_service(rd_service.isSelected());


                if (this.hotelManager.save(this.hotel)) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
