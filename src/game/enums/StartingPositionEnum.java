package game.enums;

import javafx.scene.paint.Color;

public enum StartingPositionEnum {
    RED(0), GREEN(20), BLUE(10), YELLOW(30);

    StartingPositionEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private Integer value;


}
