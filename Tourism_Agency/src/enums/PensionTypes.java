package enums;


public enum PensionTypes {

    ULTRA_ALL_INCLUSIVE("Ultra Her Şey Dahil", 1),
    ALL_INCLUSIVE("Her Şey Dahil", 2),
    ROOM_BREAKFAST("Oda Kahvaltı", 3),
    FULL_PENSION("Tam Pansiyon", 4),
    HALF_PENSION("Yarım Pansiyon", 5),
    ONLY_BED("Sadece Yatak", 6),
    NO_ALCOHOL_FULL("Alkol Hariç Full Credit", 7);

    private String name;
    private int key;

    PensionTypes(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public static PensionTypes getByValue(int key) throws ClassNotFoundException {
        for (PensionTypes p : PensionTypes.values()){
            if (p.getKey() == key){
               return p;
            }
        }
      throw new ClassNotFoundException();
    }

    public String getName() {
        return this.name;
    }

    public int getKey() {
        return key;
    }
}

