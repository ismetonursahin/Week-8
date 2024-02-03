package entity;

import java.sql.PreparedStatement;
import java.time.LocalDate;

public class Rezervation {

    private int id;
    private int room_id;
    private int total_price;
    private LocalDate check_in;
    private LocalDate check_out;
    private int guest_num;
    private String guest_name;
    private String guest_tc;
    private String guest_mail;
    private String guest_phone;
    private int day;
    private int adult_num;
    private int child_num;
    private Hotel hotel;
    private Room room;

    public Rezervation() {
    }

    public Rezervation(int adult_num, int child_num , int id, int room_id, int total_price, LocalDate check_in, LocalDate check_out, int guest_num, String guest_name, String guest_tc, String guest_mail, String guest_phone, int day, Hotel hotel, Room room) {
        this.id = id;
        this.room_id = room_id;
        this.total_price = total_price;
        this.check_in = check_in;
        this.check_out = check_out;
        this.guest_num = guest_num;
        this.guest_name = guest_name;
        this.guest_tc = guest_tc;
        this.guest_mail = guest_mail;
        this.guest_phone = guest_phone;
        this.day = day;
        this.hotel = hotel;
        this.room = room;
        this.adult_num = adult_num;
        this.child_num = child_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public LocalDate getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDate check_in) {
        this.check_in = check_in;
    }

    public LocalDate getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDate check_out) {
        this.check_out = check_out;
    }

    public int getGuest_num() {
        return guest_num;
    }

    public void setGuest_num(int guest_num) {
        this.guest_num = guest_num;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_tc() {
        return guest_tc;
    }

    public void setGuest_tc(String guest_tc) {
        this.guest_tc = guest_tc;
    }

    public String getGuest_mail() {
        return guest_mail;
    }

    public void setGuest_mail(String guest_mail) {
        this.guest_mail = guest_mail;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getAdult_num() {
        return adult_num;
    }

    public void setAdult_num(int adult_num) {
        this.adult_num = adult_num;
    }

    public int getChild_num() {
        return child_num;
    }

    public void setChild_num(int child_num) {
        this.child_num = child_num;
    }

    @Override
    public String toString() {
        return "Rezervation{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", total_price=" + total_price +
                ", check_in=" + check_in +
                ", check_out=" + check_out +
                ", guest_num=" + guest_num +
                ", guest_name='" + guest_name + '\'' +
                ", guest_tc='" + guest_tc + '\'' +
                ", guest_mail='" + guest_mail + '\'' +
                ", guest_phone='" + guest_phone + '\'' +
                ", day=" + day +
                ", hotel=" + hotel +
                ", room=" + room +
                '}';
    }
}
