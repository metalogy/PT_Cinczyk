package game;

import GUI.GameBoardController;
import game.enums.GameStatusEnum;
import game.enums.MoveTypeEnum;
import game.enums.PawnStatusEnum;

import java.util.ArrayList;

public class GameController {

    private Game game;
    private GameStatusEnum gameStatus;
    private Player currentPlayer;
    private int currentPlayerID;

    public GameController(Game game) {
        this.game = game;
        this.currentPlayerID = 0;
        this.currentPlayer = getCurrentPlayer();
    }

    public Player getCurrentPlayer() {
        return this.game.getPlayers().get(this.currentPlayerID);
    }

    public void setGameStatus(GameStatusEnum status) {
        this.game.setGameStatus(status);
    }

    public ArrayList<Player> getPlayers() {
        return this.game.getPlayers();
    }

    public void nextPlayer() {
        this.currentPlayerID++;
        if (this.currentPlayerID > game.getPlayers().size() - 1) {
            this.currentPlayerID = 0;
        }
    }

    public boolean movePawn(Player player, Integer id, Integer rolled) {
        for (Pawn pawn : player.getPawns()
        ) {
            if (pawn.getId() == id) {
                if (pawn.getStatus() == PawnStatusEnum.ON_SPAWN_POINT) {
                    if (rolled == 6) {
                        String position = "";
                        switch (checkField(player, pawn, player.getStartingBoardPositionString())) {
                            case NORMAL:
                            case PAWN_BEATING:
                                //zbicie pionka przeciwnika następuje w funkcji checkField
                                pawn.putPawnOnBoard(new String(String.valueOf(player.getStartingBoardPosition())));
                                position = "#" + (new String(String.valueOf(player.getStartingBoardPosition())));
                                System.out.println("POSITION " + position);
                                pawn.setPosition(position);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }
                    } else {
                        System.out.println("Cant move, you haven't rolled 6");
                        return false;
                    }
                } else if (pawn.getStatus() == PawnStatusEnum.ON_BOARD) {
                    Integer oldPedometer = pawn.getPedometer();
                    pawn.setPedometer(pawn.getPedometer() + rolled);

                    if (pawn.getPedometer() < 40) {
                        //normalny ruch po planszy
                        String newPawnPosition = "";
                        if (player.getStartingBoardPosition() != 0) {
                            newPawnPosition = "#" + (pawn.getPedometer() + player.getStartingBoardPosition()) % GameConstants.BOARD_SIZE;
                        } else {
                            newPawnPosition = "#" + pawn.getPedometer();
                        }
                        switch (checkField(player, pawn, newPawnPosition)) {
                            case NORMAL:
                            case PAWN_BEATING:
                                //zbicie pionka przeciwnika następuje w funkcji checkField
                                pawn.setPosition(newPawnPosition);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }
                        //#TODO może flaga dotycząca finalnie zajętych domków?
                    } else if (pawn.getPedometer() == 40) {
                        String newPawnPosition = "#end" + player.getPawnsColor() + "_1";
                        switch (checkField(player, pawn, newPawnPosition)) {
                            case NORMAL:
                                pawn.setPosition(newPawnPosition);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }

                    } else if (pawn.getPedometer() == 41) {
                        String newPawnPosition = "#end" + player.getPawnsColor() + "_2";
                        switch (checkField(player, pawn, newPawnPosition)) {
                            case NORMAL:
                                pawn.setPosition(newPawnPosition);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }
                    } else if (pawn.getPedometer() == 42) {
                        String newPawnPosition = "#end" + player.getPawnsColor() + "_3";
                        switch (checkField(player, pawn, newPawnPosition)) {
                            case NORMAL:
                                pawn.setPosition(newPawnPosition);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }
                    } else if (pawn.getPedometer() == 43) {
                        String newPawnPosition = "#end" + player.getPawnsColor() + "_4";
                        switch (checkField(player, pawn, newPawnPosition)) {
                            case NORMAL:
                                pawn.setPosition(newPawnPosition);
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }
                    } else {
                        System.out.println("Too much!");
                        //pawn.setPedometer(oldPedometer);
                        return false;
                    }
                }
                //#TODO blokowanie pionków na końcowych pozycjach w domku
                //#TODO warunki zwycięstwa
                else if (pawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
                    System.out.println("Final pawn position, cant move");
                    return false;
                }
            }

        }
        return false;
    }


    public MoveTypeEnum checkField(Player currentPlayer, Pawn pawnToMove, String field) {
        for (Player player : getPlayers()) {
            for (Pawn pawn : player.getPawns()) {
                if (pawn.getPosition().equals(field)) {
                    if (player == currentPlayer) {
                        //własny pionek, brak możliwości ruchu
                        return MoveTypeEnum.FIELD_TAKEN;
                    } else {
                        pawn.backToHome(); //powrót piona przeciwnika do domu
                        return MoveTypeEnum.PAWN_BEATING;

                    }
                }
            }

        }
        return MoveTypeEnum.NORMAL;
    }

}
