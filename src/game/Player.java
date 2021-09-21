package game;

import java.util.ArrayList;

public class Player {
    public Pawn[] getPawns() {
        return pawns;
    }

    private Pawn[] pawns = new Pawn[4];
    private PawnsColorEnum pawnsColor;
    private String login;
    private int startingPosition; //#TODO w jakiś sposób przechowywać pola startowe

    public Player(PawnsColorEnum pawnsColor, String login) {


        this.pawnsColor = pawnsColor;
        this.login = login;
        for (int i = 0; i < 4; i++) {
            String startingPosition = "home" + pawnsColor + "_" + i;
            System.out.println(startingPosition);
            Pawn pawn = new Pawn(i, pawnsColor, startingPosition);
            pawns[i] = pawn;
        }
    }

    public boolean movePawn(Integer id, Integer rolled) {
        //#TODO warunki wejscia i wyjscia pionu z bazy, sprawdzenie czy wchodzi juz do bazy
        for (Pawn pawn : pawns
        ) {
            if (pawn.getId() == id) {
                if (pawn.getStatus() == PawnStatusEnum.ON_SPAWN_POINT) {
                    if (rolled == 6) {
                        pawn.putPawnOnBoard(new String(String.valueOf(getStartingPosition())));
                        String position = "#" + (new String(String.valueOf(getStartingPosition())));
                        System.out.println("POSITION " + position);
                        pawn.setPosition(position);
                        return true;
                    } else {
                        System.out.println("Cant move");
                        return false;
                    }
                } else if (pawn.getStatus() == PawnStatusEnum.ON_BOARD) {
                    Integer oldPedometer = pawn.getPedometer();
                    pawn.setPedometer(pawn.getPedometer() + rolled);

                    if (pawn.getPedometer() < 40) {
                        if (startingPosition != 0) {
                            String newPosition = "#" + pawn.getPedometer() % startingPosition; //#TODO zmiana na domki
                            pawn.setPosition(newPosition);
                            return true;
                        } else {
                            String newPosition = "#" + pawn.getPedometer();
                            pawn.setPosition(newPosition);
                            return true;
                        }
                    } else if (pawn.getPedometer() == 40) {
                        String newPosition = "#end" + getPawnsColor() + "_1";
                        pawn.setPosition(newPosition);
                        return true;
                    } else if (pawn.getPedometer() == 41) {
                        String newPosition = "#end" + getPawnsColor() + "_2";
                        pawn.setPosition(newPosition);
                        return true;
                    } else if (pawn.getPedometer() == 42) {
                        String newPosition = "#end" + getPawnsColor() + "_3";
                        pawn.setPosition(newPosition);
                        return true;
                    } else if (pawn.getPedometer() == 43) {
                        String newPosition = "#end" + getPawnsColor() + "_4";
                        pawn.setPosition(newPosition);
                        return true;
                    } else {
                        System.out.println("Too much!");
                        pawn.setPedometer(oldPedometer);
                        return false;
                    }
                }
                //#TODO blokowanie pionków
                else if (pawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
                    System.out.println("Final pawn position, cant move");
                    return false;
                }
            }

        }
        return false;
    }

    public int getStartingPosition() {
        return startingPosition;
    }


    public String getLogin() {
        return login;
    }

    public String getPawnsColor() {
        return pawnsColor.getValue();
    }


}
