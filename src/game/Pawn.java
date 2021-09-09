package game;

public class Pawn {
    private Integer id;
    private Integer position;
    private PawnStatusEnum status;
    private PawnsColorEnum color;

    Pawn(Integer id, PawnsColorEnum color)
    {
        this.id=id;
        this.position=null;
        this.status=PawnStatusEnum.ON_SPAWN_POINT;
        this.color=color;
    }
    //#TODO
//    public void takeAPawn(Deque<Pawn> base)
//    {
//        this.position=null;
//        this.status=PawnStatus.ON_SPAWN_POINT;
//        base.add(this);
//    }
    public void putPawnOnBoard(Integer position)
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
