package game;

public class Pawn {
    private Integer id;

    public Integer getPedometer() {
        return pedometer;
    }

    public void setPedometer(Integer pedometer) {
        this.pedometer = pedometer;
    }

    private Integer pedometer;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String position;
    private PawnStatusEnum status;
    private PawnsColorEnum color;

    Pawn(Integer id, PawnsColorEnum color, String startingPosition)
    {
        this.id=id;
        this.position=startingPosition;
        this.status=PawnStatusEnum.ON_SPAWN_POINT;
        this.color=color;
        this.pedometer=0;
    }
    //#TODO
//    public void takeAPawn(Deque<Pawn> base)
//    {
//        this.position=null;
//        this.status=PawnStatus.ON_SPAWN_POINT;
//        base.add(this);
//    }
    public void putPawnOnBoard(String position)
    {
        this.position=position;
        this.status=PawnStatusEnum.ON_BOARD;
    }
    public PawnStatusEnum getStatus()
    {
        return this.status;
    }

    public Integer getId() {
        return id;
    }
}
