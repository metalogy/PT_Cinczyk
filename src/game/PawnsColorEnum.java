package game;

import javafx.scene.paint.Color;

public enum PawnsColorEnum {
    Red("Red"),Green("Green"),Blue("Blue"),Yellow("Yellow");

    PawnsColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

//    @Override
//    public String toString() {
//        return "PawnsColorEnum{" +
//                "value='" + value + '\'' +
//                '}';
//    }

    private String value;


}
