package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import enums.PensionTypes;

import javax.swing.*;

public class PensionView extends Layout{
    private JPanel container;
    private JComboBox<ComboItem> cmb_pension_type;
    private JButton btn_pension_save;
    private Hotel hotel;
    private PensionTypes pensionTypes ;
    private  Pension pension;
    private HotelManager hotelManager;
    private PensionManager pensionManager;


    public PensionView(Hotel hotel){
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(500, 300);

        loadPensionTypes();  // combo panisyon tipleri

    }

    public void loadPensionTypes(){

       for(PensionTypes p : PensionTypes.values()){
        ComboItem pensionTypeCmbItem = new ComboItem(p.getKey(),p.getName());
        this.cmb_pension_type.addItem(pensionTypeCmbItem);
       };

        btn_pension_save.addActionListener(e -> {

//            ComboItem selectedPension = (ComboItem) cmb_pension_type.getSelectedItem();
//            this.pension.setPensionType((PensionTypes) cmb_pension_type.getSelectedItem());

            ComboItem selectedPensionItem = (ComboItem) cmb_pension_type.getSelectedItem();
            int selectedPensionKey = selectedPensionItem.getKey();
            PensionTypes selectedPensionType = null;

           try {
                selectedPensionType = PensionTypes.getByValue(selectedPensionKey);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            this.pension.setPensionType(selectedPensionType);
            this.pension.setHotelId(hotel.getId());


            if (this.pensionManager.save(pension)) {
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }
        });
    }

}
