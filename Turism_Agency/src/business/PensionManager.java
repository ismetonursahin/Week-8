package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.User;

import java.util.ArrayList;

public class PensionManager {

    private final PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao=new PensionDao();
    }

    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> getByHotelId(int hotelId) {
        return this.pensionDao.getByHotelId(hotelId);
    }

    public ArrayList<Object[]> getForTable(int size , ArrayList<Pension> pensionList){
        ArrayList<Object[]> pensionRowList = new ArrayList<>();
        for(Pension pension : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getHotelId();
            rowObject[i++] = pension.getPensionType().getName();
            pensionRowList.add(rowObject);
        }
        return pensionRowList;
    }


    public boolean save(Pension pension) {
        if (this.getById(pension.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }

}
