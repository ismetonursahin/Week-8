package business;

import core.Helper;
import dao.RoomDao;
import entity.Pension;
import entity.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {

    private final RoomDao roomDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size , ArrayList<Room> roomList){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : roomList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getHotel_id();
            rowObject[i++] = room.getPension_type_id().getName();
            rowObject[i++] = room.getRoom_type().getName();
            rowObject[i++] = room.getSeason_id();
            rowObject[i++] = room.getStock();
            rowObject[i++] = room.getAdult_price();
            rowObject[i++] = room.getChild_price();
            rowObject[i++] = room.getBed();
            rowObject[i++] = room.getMeter();
            rowObject[i++] = room.isTv();
            rowObject[i++] = room.isMinibar();
            rowObject[i++] = room.isConsol();
            rowObject[i++] = room.isSafe();
            rowObject[i++] = room.isCondition();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

    public boolean save(Room room) {
        if (this.getById(room.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean decreaseRoomStock(int id) throws SQLException {
        return this.roomDao.decreaseRoomStock(id);
    }
    public boolean increaseRoomStock(int id) throws SQLException {
        return this.roomDao.increaseRoomStock(id);
    }

    public ArrayList<Room> searchForRoom(int hotelId ,String city ,String strt_date, String fnsh_date,String adult_num,String child_num) {
        String query = "SELECT * FROM rooms r " +
                "JOIN hotels h ON r.hotel_id = h.id " +
                "LEFT JOIN seasons s ON r.season_id = s.id";


        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();


        strt_date = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (hotelId != 0) {
            where.add("h.id = '" + hotelId + "'" );
        }
        if (city != null) {
            where.add("h.city = '" + city + "'");
        }

        if (adult_num != null && !adult_num.isEmpty() && child_num != null && !child_num.isEmpty()) {
            try {
                int adultNum = Integer.parseInt(adult_num);
                int childNum = Integer.parseInt(child_num);
                int total_person = adultNum+childNum;
                where.add("r.bed >= '" + (total_person) + "'");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        where.add("(s.start_date <= '" + strt_date + "')");
        where.add("(s.finish_date >= '" + fnsh_date + "')");

        where.add("r.stock > 0");

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (joinStr.length() > 0) {    //?
            query += " ON " + joinStr;
        }

        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }



        System.out.println(query);

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        //    String bookOrWhereStr = String.join(" OR ", bookOrWhere);
        //  String bookQuery = "SELECT * fROM public.book WHERE " + bookOrWhereStr;
//
//        ArrayList<Book> bookList = this.bookDao.selectByQuery(bookQuery);
//        ArrayList<Integer> busyCarId = new ArrayList<>();
//        for (Book book : bookList) {
//            busyCarId.add(book.getCar_id());
//        }

//        searchedCarList.removeIf(car -> busyCarId.contains(car.getId()));

      //  ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        return this.roomDao.selectByQuery(query);
    }



}
