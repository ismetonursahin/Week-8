package dao;

import core.Db;
import entity.Pension;
import entity.User;
import enums.PensionTypes;

import java.sql.*;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con ;

    public PensionDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Pension> findAll(){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension_types";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                pensionList.add(this.match(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
      return pensionList;
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension_types WHERE id = ?";
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

    public ArrayList<Pension> getByHotelId(int hotelId) {
        ArrayList<Pension> obj = new ArrayList<>();
        String query = "SELECT * FROM public.pension_types WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj.add(this.match(rs));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension_types " +
                "(" +
                "hotel_id, " +
                "type " +
                ")" +
                " VALUES(?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setInt(2, pension.getPensionType().getKey());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }



    public Pension match(ResultSet rs) throws SQLException, ClassNotFoundException {
        Pension obj = new Pension();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPensionType(PensionTypes.getByValue(rs.getInt("type")));
        return obj;
    }

}
