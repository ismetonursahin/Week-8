package business;

import core.Helper;
import dao.PensionDao;
import dao.SeasonDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {

    private final SeasonDao seasonDao;

    public SeasonManager(){
        this.seasonDao=new SeasonDao();
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }
    public ArrayList<Season> getByHotelId(int hotelId) {
        return this.seasonDao.getByHotelId(hotelId);
    }

    public ArrayList<Object[]> getForTable(int size , ArrayList<Season> seasonList){
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for(Season season : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getId();
            rowObject[i++] = season.getHotel_id();
            rowObject[i++] = season.getStart_date().toString();
            rowObject[i++] = season.getFinish_date().toString();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }
    public boolean save(Season season) {
        if (this.getById(season.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

}
