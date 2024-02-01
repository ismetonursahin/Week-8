package enums;

public enum RoomTypes {

    SINGLE_ROOM("Tek Kişilik Oda", 1),
    DOUBLE_ROOM("Çift Kişilik Oda",2),
    JR_SUIT_ROOM("Jr Suit Oda",3),
    SUIT_ROOM("Suit Oda" , 4);

    private String name;
    private int key;

    RoomTypes(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public static RoomTypes getByValue(int key) throws ClassNotFoundException {
        for (RoomTypes room : RoomTypes.values()){
            if (room.getKey() == key){
                return room;
            }
        }
        throw new ClassNotFoundException();
    }
    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }
}
