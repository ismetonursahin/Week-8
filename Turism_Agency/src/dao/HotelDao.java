package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotels";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotels " +
                "(" +
                "name," +
                "city," +
                "region," +
                "adress," +
                "mail," +
                "phone," +
                "star," +
                "park," +
                "wifi," +
                "pool," +
                "fitness," +
                "concierge," +
                "spa," +
                "room_service" +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1,hotel.getName());
            pr.setString(2,hotel.getCity());
            pr.setString(3,hotel.getRegion());
            pr.setString(4,hotel.getAdress());
            pr.setString(5,hotel.getMail());
            pr.setString(6,hotel.getPhone());
            pr.setInt(7,hotel.getStar());
            pr.setBoolean(8, hotel.isPark());
            pr.setBoolean(9, hotel.isWifi());
            pr.setBoolean(10, hotel.isPool());
            pr.setBoolean(11, hotel.isFitness());
            pr.setBoolean(12, hotel.isConcierge());
            pr.setBoolean(13, hotel.isSpa());
            pr.setBoolean(14, hotel.isRoom_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setCity(rs.getString("city"));
        obj.setRegion(rs.getString("region"));
        obj.setAdress(rs.getString("adress"));
        obj.setMail(rs.getString("mail"));
        obj.setPhone(rs.getString("phone"));
        obj.setStar(rs.getInt("star"));
        obj.setPark(rs.getBoolean("park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));

        return obj;
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotels WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }
}
