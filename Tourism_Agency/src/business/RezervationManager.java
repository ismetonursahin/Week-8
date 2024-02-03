package business;

import core.Helper;
import dao.RezervationDao;
import entity.Rezervation;
import entity.Room;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RezervationManager {
    private final RezervationDao rezervationDao;

    public RezervationManager(){
        this.rezervationDao= new RezervationDao();
    }

    public ArrayList<Rezervation> findAll(){
        return this.rezervationDao.findAll();
    }

    public Rezervation getById(int id) {
        return this.rezervationDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size , ArrayList<Rezervation> rezervationList){
        ArrayList<Object[]> rezervationRowList = new ArrayList<>();
        for(Rezervation rezervation : rezervationList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = rezervation.getId();
            rowObject[i++] = rezervation.getRoom().getId();
            rowObject[i++] = rezervation.getGuest_name();
            rowObject[i++] = rezervation.getGuest_tc();
            rowObject[i++] = rezervation.getGuest_mail();
            rowObject[i++] = rezervation.getGuest_phone();
            rowObject[i++] = rezervation.getCheck_in();
            rowObject[i++] = rezervation.getCheck_out();
            rowObject[i++] = rezervation.getDay();
            rowObject[i++] = rezervation.getTotal_price();
            rowObject[i++] = rezervation.getGuest_num();
            rezervationRowList.add(rowObject);
        }
        return rezervationRowList;
    }

    public boolean update(Rezervation rezervation) {
        if (this.getById(rezervation.getId()) == null) {
            Helper.showMsg(rezervation.getId() + "ID Kayıtlı Rezervasyon Bulunamadı.");
            return false;
        }
        return this.rezervationDao.update(rezervation);
    }

    public boolean save(Rezervation rezervation) {
        if (this.getById(rezervation.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.rezervationDao.save(rezervation);
    }


    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID Kayıtlı Rezervasyon Bulunamadı.");
            return false;
        }
        return this.rezervationDao.delete(id);
    }


}
