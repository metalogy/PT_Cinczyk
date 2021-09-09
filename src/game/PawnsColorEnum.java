package game;

public enum PawnsColorEnum {
    R(1),G(2),B(3),Y(4);

    PawnsColorEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private Integer value;


}
