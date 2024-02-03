package entity;

import enums.PensionTypes;

public class Pension {
    private int id;
    private int hotelId;
    private PensionTypes pensionType;
    private Hotel hotel;


    public Pension(int id, int hotelId, PensionTypes pensionType) {
        this.id = id;
        this.hotelId = hotelId;
        this.pensionType = pensionType;

    }

    public Pension() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public PensionTypes getPensionType() {
        return pensionType;
    }

    public void setPensionType(PensionTypes pensionType) {
        this.pensionType = pensionType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Pension{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", pensionType=" + pensionType +
                '}';
    }
}
