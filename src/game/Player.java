package game;

import java.util.ArrayList;

public class Player {
    public Pawn[] getPawns() {
        return pawns;
    }

    private Pawn[] pawns =new Pawn[4];
    private PawnsColorEnum pawnsColor;
    private String login;

    public Player(PawnsColorEnum pawnsColor, String login) {


        this.pawnsColor = pawnsColor;
        this.login = login;
        for(int i=0;i<4;i++)
        {
            String startingPosition="home"+pawnsColor+"_"+i;
            System.out.println(startingPosition);
            Pawn pawn=new Pawn(i,pawnsColor,startingPosition);
            pawns[i]=pawn;
        }
    }

    public void movePawn(int id, Integer movement)
    {
        //#TODO warunki wejscia i wyjscia pionu z bazy, sprawdzenie czy wchodzi juz do bazy
        for (Pawn pawn:pawns
             ) {
            if(pawn.getId()==id)
            {
                pawn.setPedometer(pawn.getPedometer()+movement);
                String newPosition="#"+pawn.getPedometer().toString(); //#TODO zmiana na domki
                pawn.setPosition(newPosition);
            }

        }
    }


    public String getLogin() {
        return login;
    }

    public PawnsColorEnum getPawnsColor() {
        return pawnsColor;
    }


}
