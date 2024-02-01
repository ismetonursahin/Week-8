package entity;

import enums.PensionTypes;
import enums.RoomTypes;

public class Room {
  private int id;
  private int hotel_id;
  private PensionTypes pension_type_id;
  private RoomTypes room_type;
  private int stock;
  private Hotel hotel;
  private int adult_price;
  private int child_price;
  private int meter;
  private int season_id;
  private boolean tv;
  private boolean minibar;
  private boolean consol;
  private boolean safe;
  private boolean condition;
  private int bed;

    public Room(){
    }
    public Room(int id, int hotel_id, PensionTypes pension_type_id, RoomTypes room_type, int stock, int adult_price, int child_price, int meter, int season_id, boolean tv, boolean minibar, boolean consol, boolean safe, boolean condition, int bed) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.pension_type_id = pension_type_id;
        this.room_type = room_type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.meter = meter;
        this.season_id = season_id;
        this.tv = tv;
        this.minibar = minibar;
        this.consol = consol;
        this.safe = safe;
        this.condition = condition;
        this.bed = bed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public PensionTypes getPension_type_id() {
        return pension_type_id;
    }

    public void setPension_type_id(PensionTypes pension_type_id) {
        this.pension_type_id = pension_type_id;
    }

    public RoomTypes getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomTypes room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getMeter() {
        return meter;
    }

    public void setMeter(int meter) {
        this.meter = meter;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isConsol() {
        return consol;
    }

    public void setConsol(boolean consol) {
        this.consol = consol;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", pension_type_id=" + pension_type_id +
                ", room_type=" + room_type +
                ", stock=" + stock +
                ", hotel=" + hotel +
                ", adult_price=" + adult_price +
                ", child_price=" + child_price +
                ", meter=" + meter +
                ", season_id=" + season_id +
                ", tv=" + tv +
                ", minibar=" + minibar +
                ", consol=" + consol +
                ", safe=" + safe +
                ", condition=" + condition +
                ", bed=" + bed +
                '}';
    }
}
