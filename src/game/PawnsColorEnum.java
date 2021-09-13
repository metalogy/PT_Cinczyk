package game;

public enum PawnsColorEnum {
    Red(1),Green(2),Blue(3),Yellow(4);

    PawnsColorEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private Integer value;


}
