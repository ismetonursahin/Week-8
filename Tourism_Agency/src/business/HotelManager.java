package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for(Hotel hotel : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getCity();
            rowObject[i++] = hotel.getRegion();
            rowObject[i++] = hotel.getAdress();
            rowObject[i++] = hotel.getMail();
            rowObject[i++] = hotel.getPhone();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.isPark();
            rowObject[i++] = hotel.isWifi();
            rowObject[i++] = hotel.isPool();
            rowObject[i++] = hotel.isFitness();
            rowObject[i++] = hotel.isConcierge();
            rowObject[i++] = hotel.isSpa();
            rowObject[i++] = hotel.isRoom_service();
            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }

    public boolean save(Hotel hotel) {
        if (this.getById(hotel.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }
}
