package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;
import enums.PensionTypes;
import enums.RoomTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_pension_type;
    private JComboBox cmb_seasons;
    private JComboBox cmb_room_types;
    private JTextField fld_adult_price;
    private JTextField fld_stock;
    private JTextField fld_child_price;
    private JTextField fld_bed_capasity;
    private JTextField fld_meters;
    private JRadioButton rd_tv;
    private JRadioButton rd_minibar;
    private JRadioButton rd_consol;
    private JRadioButton rd_safe;
    private JRadioButton rd_condition;
    private JButton btn_room_save;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ArrayList<Hotel> hotels;

    public RoomView() {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.hotels = new ArrayList<>();
        add(container);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Turism Agency - Room");
        this.setSize(650, 500);
        this.setVisible(true);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));


        for (Hotel hotel : hotelManager.findAll()) {
            this.hotels.add(hotel);
            ComboItem comboItem = new ComboItem(hotel.getId(), hotel.getName());
            this.cmb_hotel_name.addItem(comboItem);
        }

        loadPensionsFromSelectedCombo();
        loadRoomTypes();
        loadSeasonFromSelectedCombo();

        cmb_hotel_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPensionsFromSelectedCombo();
                loadSeasonFromSelectedCombo();
            }
        });

        btn_room_save.addActionListener(e -> {
            JTextField[] checkFieldlist  = {
                    this.fld_adult_price,
                    this.fld_child_price,
                    this.fld_bed_capasity,
                    this.fld_meters,
                    this.fld_stock
            };
            if (Helper.isFieldListEmpty(checkFieldlist)){
                Helper.showMsg("fill");
            }else {
                Room room = new Room();
                ComboItem pensionComboItem = (ComboItem) cmb_pension_type.getSelectedItem();
                try {
                    room.setPension_type_id(PensionTypes.getByValue(pensionComboItem.getKey()));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                ComboItem hotelComboItem = (ComboItem) cmb_hotel_name.getSelectedItem();
                room.setHotel_id(hotelComboItem.getKey());

                ComboItem roomTypeComboItem = (ComboItem) cmb_room_types.getSelectedItem();
                try {
                    room.setRoom_type(RoomTypes.getByValue(roomTypeComboItem.getKey()) );
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                ComboItem seasonComboItem = (ComboItem) cmb_seasons.getSelectedItem();
                room.setSeason_id(seasonComboItem.getKey());

                room.setAdult_price(Integer.parseInt(fld_adult_price.getText()));
                room.setChild_price(Integer.parseInt(fld_child_price.getText()));
                room.setBed(Integer.parseInt(fld_bed_capasity.getText()));
                room.setMeter(Integer.parseInt(fld_meters.getText()));
                room.setStock(Integer.parseInt(fld_stock.getText()));
                room.setTv(rd_tv.isSelected());
                room.setSafe(rd_safe.isSelected());
                room.setMinibar(rd_minibar.isSelected());
                room.setConsol(rd_consol.isSelected());
                room.setCondition(rd_condition.isSelected());

                if(this.roomManager.save(room)){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }

        });
    }



    private void loadPensionsFromSelectedCombo() {
        cmb_pension_type.removeAllItems();
        ComboItem selectedHotelCombo = (ComboItem) cmb_hotel_name.getSelectedItem();
        int hotelId = selectedHotelCombo.getKey();
        for (Pension pension : pensionManager.getByHotelId(hotelId)) {
            ComboItem comboItem = new ComboItem(pension.getPensionType().getKey(), pension.getPensionType().getName());
            cmb_pension_type.addItem(comboItem);
        }
    }

    private  void loadSeasonFromSelectedCombo(){
        cmb_seasons.removeAllItems();
        ComboItem selectedHotelCombo = (ComboItem) cmb_hotel_name.getSelectedItem();
        int hotelId = selectedHotelCombo.getKey();
        for (Season season : seasonManager.getByHotelId(hotelId)){
            ComboItem comboItem = new ComboItem(season.getId(),(season.getStart_date() + " - " +season.getFinish_date()));
            cmb_seasons.addItem(comboItem);
        }

    }

    private void loadRoomTypes() {
        for (RoomTypes roomType : RoomTypes.values()) {
            ComboItem comboItem = new ComboItem(roomType.getKey(), roomType.getName());
            cmb_room_types.addItem(comboItem);
        }
    }
}
