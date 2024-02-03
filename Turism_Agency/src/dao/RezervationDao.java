package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Rezervation;
import entity.Room;
import enums.PensionTypes;
import enums.RoomTypes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RezervationDao {

    private final Connection con;
    private RoomDao roomDao = new RoomDao();

    public RezervationDao() {
        this.con = Db.getInstance();
    }

    public Rezervation getById(int id) {
        Rezervation obj = null;
        String query = "SELECT * FROM public.rezervation WHERE id = ?";
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

    public ArrayList<Rezervation> findAll() {
        ArrayList<Rezervation> rezervationList = new ArrayList<>();
        String sql = "SELECT * FROM public.rezervation";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                rezervationList.add(this.match(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rezervationList;
    }


    public Rezervation match(ResultSet rs) throws SQLException, ClassNotFoundException {
        Rezervation obj = new Rezervation();
        obj.setId(rs.getInt("id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setTotal_price(rs.getInt("total_price"));
        obj.setCheck_in(LocalDate.parse(rs.getString("check_in")));
        obj.setCheck_out(LocalDate.parse(rs.getString("check_out")));
        obj.setGuest_num(rs.getInt("guest_num"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuest_tc(rs.getString("guest_tc"));
        obj.setGuest_mail(rs.getString("guest_mail"));
        obj.setGuest_phone(rs.getString("guest_phone"));
        obj.setDay(rs.getInt("day"));
        obj.setAdult_num(rs.getInt("adult_num"));
        obj.setChild_num(rs.getInt("child_num"));
        obj.setRoom(this.roomDao.getById(rs.getInt("room_id")));
        return obj;
    }

    public boolean update(Rezervation rezervation){
        String query = "UPDATE public.rezervation SET " +
                "guest_name = ? , " +
                "guest_tc = ? , " +
                "guest_mail = ? , " +
                "guest_phone = ? , " +
                "total_price = ? ," +
                "guest_num = ?  " +
                " WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, rezervation.getGuest_name());
            pr.setString(2, rezervation.getGuest_tc());
            pr.setString(3, rezervation.getGuest_mail());
            pr.setString(4, rezervation.getGuest_phone());
            pr.setInt(5,rezervation.getTotal_price());
            pr.setInt(6,rezervation.getGuest_num());
            pr.setInt(7,rezervation.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.rezervation WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return true;
    }
    public boolean save(Rezervation rezervation){
        String query = "INSERT INTO public.rezervation " +
                "(" +
                "room_id, " +
                "total_price, " +
                "check_in, " +
                "check_out, " +
                "guest_num, " +
                "guest_name, " +
                "guest_tc, " +
                "guest_mail, " +
                "guest_phone, " +
                "day, " +
                "adult_num, " +
                "child_num " +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, rezervation.getRoom_id());
            pr.setInt(2,rezervation.getTotal_price());
            pr.setDate(3, Date.valueOf(rezervation.getCheck_in()));
            pr.setDate(4, Date.valueOf(rezervation.getCheck_out()));
            pr.setInt(5,rezervation.getGuest_num());
            pr.setString(6, rezervation.getGuest_name());
            pr.setString(7, rezervation.getGuest_tc());
            pr.setString(8, rezervation.getGuest_mail());
            pr.setString(9, rezervation.getGuest_phone());
            pr.setInt(10,rezervation.getDay());
            pr.setInt(11,rezervation.getAdult_num());
            pr.setInt(12,rezervation.getChild_num());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

}
