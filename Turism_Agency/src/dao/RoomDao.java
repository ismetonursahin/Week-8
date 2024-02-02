package dao;

import core.Db;
import entity.Pension;
import entity.Room;
import enums.PensionTypes;
import enums.RoomTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con ;
    public HotelDao hotelDao ;
    public SeasonDao seasonDao;
    public PensionDao pensionDao;

    public RoomDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.seasonDao = new SeasonDao();
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Room> findAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.rooms";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                roomList.add(this.match(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return roomList;
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.rooms WHERE id = ?";
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

    public ArrayList<Room> getByHotelId(int hotelId) {
        ArrayList<Room> obj = new ArrayList<>();
        String query = "SELECT * FROM public.rooms WHERE hotel_id = ?";
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
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> room = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                room.add(this.match(rs));
            }

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }

        return room;
    }

    public boolean decreaseRoomStock(int id) throws SQLException {
        String query = "UPDATE public.rooms SET stock = stock-1 WHERE id = ?";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1,id);
        return pr.executeUpdate() != -1;
    }
    public boolean increaseRoomStock(int id) throws SQLException {
        String query = "UPDATE public.rooms SET stock = stock+1 WHERE id = ?";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1,id);
        return pr.executeUpdate() != -1;
    }


    public boolean save(Room room){
        String query = "INSERT INTO public.rooms" +
                "(" +
                "hotel_id, " +
                "pension_type_id, " +
                "room_type, " +
                "stock, " +
                "adult_price, " +
                "child_price, " +
                "meter, " +
                "tv, " +
                "minibar, " +
                "consol, " +
                "safe, " +
                "condition, " +
                "season_id, " +
                "bed " +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_type_id().getKey());
            pr.setInt(3,room.getRoom_type().getKey());
            pr.setInt(4,room.getStock());
            pr.setInt(5,room.getAdult_price());
            pr.setInt(6,room.getChild_price());
            pr.setInt(7,room.getMeter());
            pr.setBoolean(8, room.isTv());
            pr.setBoolean(9, room.isMinibar());
            pr.setBoolean(10, room.isConsol());
            pr.setBoolean(11, room.isSafe());
            pr.setBoolean(12, room.isCondition());
            pr.setInt(13,room.getSeason_id());
            pr.setInt(14,room.getBed());

            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }



    public Room match(ResultSet rs) throws SQLException, ClassNotFoundException {
        Room obj = new Room();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setRoom_type(RoomTypes.getByValue(rs.getInt("room_type")));
        obj.setPension_type_id(PensionTypes.getByValue(rs.getInt("pension_type_id")));
        obj.setBed(rs.getInt("bed"));
        obj.setAdult_price(rs.getInt("adult_price"));
        obj.setChild_price(rs.getInt("child_price"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setMeter(rs.getInt("meter"));
        obj.setStock(rs.getInt("stock"));
        obj.setTv(rs.getBoolean("tv"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setConsol(rs.getBoolean("consol"));
        obj.setSafe(rs.getBoolean("safe"));
        obj.setCondition(rs.getBoolean("condition"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("season_id")));
        return obj;
    }
}
