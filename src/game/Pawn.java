package game;

import game.enums.PawnStatusEnum;
import game.enums.PawnsColorEnum;

public class Pawn {
    private Integer id;
    private Integer pedometer;
    private String position;
    private PawnStatusEnum status;
    private PawnsColorEnum color;
    private String originalHomePosition;

    Pawn(Integer id, PawnsColorEnum color, String startingPosition) {
        this.id = id;
        this.position = startingPosition;
        this.originalHomePosition = startingPosition;
        this.status = PawnStatusEnum.ON_SPAWN_POINT;
        this.color = color;
        this.pedometer = 0;
    }

    public void backToHome() {
        this.position = this.originalHomePosition;
        this.status = PawnStatusEnum.ON_SPAWN_POINT;
    }

    public void putPawnOnBoard(String position) {
        this.position = position;
        this.status = PawnStatusEnum.ON_BOARD;
    }

    public PawnStatusEnum getStatus() {
        return this.status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPedometer() {
        return pedometer;
    }

    public void setPedometer(Integer pedometer) {
        this.pedometer = pedometer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(PawnStatusEnum status) {
        this.status = status;
    }
}
