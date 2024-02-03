package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SeasonView extends Layout{

    private JTextField fld_season_start_date;
    private JTextField fld_season_finish_date;
    private JButton btn_season_save;
    private JPanel container;
    private JLabel lbl_agency;
    private Hotel hotel;
    private HotelManager hotelManager;
    private Season season;
    private SeasonManager seasonManager;


    public SeasonView(Hotel hotel) {
        this.season = new Season();
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        guiInitilaze(600, 450);
        this.setTitle("Turism Agency - Season");


        this.lbl_agency.setText("Otel: " + this.hotel.getName());


        btn_season_save.addActionListener(e -> {
            JTextField[] checkFieldlist  = {
                    this.fld_season_start_date,
                    this.fld_season_finish_date
            };
            if (Helper.isFieldListEmpty(checkFieldlist)){
                Helper.showMsg("fill");
            }else {
                Season season = new Season();
              season.setStart_date(LocalDate.parse(this.fld_season_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
              season.setFinish_date(LocalDate.parse(this.fld_season_finish_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
              season.setHotel_id(this.hotel.getId());

              if(this.seasonManager.save(season)){
                  Helper.showMsg("done");
                  dispose();
              }else{
                  Helper.showMsg("error");
              }
            }

        });
    }
    private void createUIComponents() throws ParseException {
        this.fld_season_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_start_date.setText("16/12/2021");
        this.fld_season_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_season_finish_date.setText("16/12/2022");
    }



}
