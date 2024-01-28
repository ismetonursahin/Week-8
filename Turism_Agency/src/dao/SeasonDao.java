package dao;

import core.Db;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con ;

    public SeasonDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAll(){
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.seasons";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                seasonList.add(this.match(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return seasonList;
    }
    public boolean save(Season season){
        String query = "INSERT INTO public.seasons " +
                "(" +
                "hotel_id, " +
                "start_date, " +
                "finish_date " +
                ")" +
                " VALUES(?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.seasons WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public Season match(ResultSet rs)throws SQLException {
        Season season = new Season();

        season.setId(rs.getInt("id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setStart_date(LocalDate.parse(rs.getString("start_date")));
        season.setFinish_date(LocalDate.parse(rs.getString("finish_date")));

        return season;
    }
}
