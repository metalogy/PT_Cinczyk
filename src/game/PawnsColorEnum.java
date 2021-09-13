package game;

import javafx.scene.paint.Color;

public enum PawnsColorEnum {
    Red(Color.RED),Green(Color.GREEN),Blue(Color.BLUE),Yellow(Color.YELLOW);

    PawnsColorEnum(Color color) {
        this.value = value;
    }

    public Color getValue() {
        return value;
    }

    private Color value;


}
