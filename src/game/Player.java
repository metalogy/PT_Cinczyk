package game;

import game.enums.PawnStatusEnum;
import game.enums.PawnsColorEnum;
import game.enums.StartingPositionEnum;

public class Player {
    private Pawn[] pawns = new Pawn[4];
    private PawnsColorEnum pawnsColor;
    private String login;
    private int startingBoardPosition;
    private String startingBoardPositionString;

    public Player(PawnsColorEnum pawnsColor, String login) {
        this.pawnsColor = pawnsColor;
        this.login = login;
        for (int i = 0; i < 4; i++) {
            String startingPosition = "home" + pawnsColor + "_" + i;
            System.out.println(startingPosition);
            Pawn pawn = new Pawn(i, pawnsColor, startingPosition);
            pawns[i] = pawn;
            switch (this.pawnsColor) {
                case Red:
                    this.startingBoardPosition = StartingPositionEnum.RED.getValue();
                    this.startingBoardPositionString = "#" + this.startingBoardPosition;
                    break;
                case Blue:
                    this.startingBoardPosition = StartingPositionEnum.BLUE.getValue();
                    this.startingBoardPositionString = "#" + this.startingBoardPosition;
                    break;
                case Green:
                    this.startingBoardPosition = StartingPositionEnum.GREEN.getValue();
                    this.startingBoardPositionString = "#" + this.startingBoardPosition;
                    break;
                case Yellow:
                    this.startingBoardPosition = StartingPositionEnum.YELLOW.getValue();
                    this.startingBoardPositionString = "#" + this.startingBoardPosition;
                    break;
            }
        }
    }
    public Pawn[] getPawns() {
        return pawns;
    }

    public String getStartingBoardPositionString() {
        return startingBoardPositionString;
    }

//    public boolean movePawn(Integer id, Integer rolled) {
//        for (Pawn pawn : pawns
//        ) {
//            if (pawn.getId() == id) {
//                if (pawn.getStatus() == PawnStatusEnum.ON_SPAWN_POINT) {
//                    if (rolled == 6) {
//                        pawn.putPawnOnBoard(new String(String.valueOf(getStartingBoardPosition())));
//                        String position = "#" + (new String(String.valueOf(getStartingBoardPosition())));
//                        System.out.println("POSITION " + position);
//                        pawn.setPosition(position);
//                        return true;
//                    } else {
//                        System.out.println("Cant move");
//                        return false;
//                    }
//                } else if (pawn.getStatus() == PawnStatusEnum.ON_BOARD) {
//                    Integer oldPedometer = pawn.getPedometer();
//                    pawn.setPedometer(pawn.getPedometer() + rolled);
//
//                    if (pawn.getPedometer() < 40) {
//                        if (startingBoardPosition != 0) {
//                            String newPosition = "#" + (pawn.getPedometer() + startingBoardPosition) % GameConstants.BOARD_SIZE;
//                            pawn.setPosition(newPosition);
//                            return true;
//                        } else {
//                            String newPosition = "#" + pawn.getPedometer();
//                            pawn.setPosition(newPosition);
//                            return true;
//                        }
//                    } else if (pawn.getPedometer() == 40) {
//                        String newPosition = "#end" + getPawnsColor() + "_1";
//                        pawn.setPosition(newPosition);
//                        return true;
//                    } else if (pawn.getPedometer() == 41) {
//                        String newPosition = "#end" + getPawnsColor() + "_2";
//                        pawn.setPosition(newPosition);
//                        return true;
//                    } else if (pawn.getPedometer() == 42) {
//                        String newPosition = "#end" + getPawnsColor() + "_3";
//                        pawn.setPosition(newPosition);
//                        return true;
//                    } else if (pawn.getPedometer() == 43) {
//                        String newPosition = "#end" + getPawnsColor() + "_4";
//                        pawn.setPosition(newPosition);
//                        pawn.setStatus(PawnStatusEnum.LOCKED_IN_HOME);
//                        return true;
//                    } else {
//                        System.out.println("Too much!");
//                        //pawn.setPedometer(oldPedometer);
//                        return false;
//                    }
//                }
//                //#TODO blokowanie pionków na końcowych pozycjach w domku
//                //#TODO warunki zwycięstwa
//                else if (pawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
//                    System.out.println("Final pawn position, cant move");
//                    return false;
//                }
//            }
//
//        }
//        return false;
//    }

    public int getStartingBoardPosition() {
        return startingBoardPosition;
    }

    public String getLogin() {
        return login;
    }

    public String getPawnsColor() {
        return pawnsColor.getValue();
    }


}
