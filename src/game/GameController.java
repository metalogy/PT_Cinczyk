package game;

import game.enums.GameStatusEnum;
import game.enums.MoveTypeEnum;
import game.enums.PawnStatusEnum;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

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
                        switch (checkField(player, player.getStartingBoardPositionString())) {
                            case NORMAL:
                            case PAWN_BEATING:
                                //zbicie pionka przeciwnika następuje w funkcji checkField
                                pawn.putPawnOnBoard(new String(String.valueOf(player.getStartingBoardPosition())));
                                position = "#" + (new String(String.valueOf(player.getStartingBoardPosition())));
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
                        switch (checkField(player, newPawnPosition)) {
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
                    } else if (pawn.getPedometer() >= 40 && pawn.getPedometer() < 44) {
                        String newPawnPosition = "";
                        switch (pawn.getPedometer()) {
                            case 40 -> newPawnPosition = "#end" + player.getPawnsColor() + "_1";
                            case 41 -> newPawnPosition = "#end" + player.getPawnsColor() + "_2";
                            case 42 -> newPawnPosition = "#end" + player.getPawnsColor() + "_3";
                            case 43 -> newPawnPosition = "#end" + player.getPawnsColor() + "_4";
                            //
                        }
                        switch (checkField(player, newPawnPosition)) {
                            case NORMAL:
                                pawn.setPosition(newPawnPosition);
                                if (checkIfLocked(player, pawn)) {
                                    pawn.setStatus(PawnStatusEnum.LOCKED_IN_HOME); //
                                }
                                return true;
                            case FIELD_TAKEN:
                                //pole zajęte przez nasz pionek
                                pawn.setPedometer(oldPedometer); //nie można ruszyć, przypisanie starego krokomierza
                                System.out.println("Cant move, field already occupied bo your pawn");
                                return false;
                        }

                    } else {
                        System.out.println("Too much!");
                        pawn.setPedometer(oldPedometer);
                        return false;
                    }
                } else if (pawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
                    System.out.println("Final pawn position, cant move");
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkWin(Player player) {
        if (WinningConditionController.check(player)) {
            this.game.setWinner(player);
            this.game.setGameStatus(GameStatusEnum.FINISHED);
            return true;
        }
        return false;
    }

    public boolean checkIfLocked(Player player, Pawn pawn) {
        int endPosition = parseInt(pawn.getPosition().substring(pawn.getPosition().length() - 1)); //pola końcowego
        if (endPosition != 4) {
            for (Pawn otherPawn : player.getPawns()) {
                if (otherPawn.getPosition().equals("#end" + player.getPawnsColor() + (endPosition + 1)))//sprawdzamy pionek pole "do przodu"
                {
                    if (otherPawn.getStatus() == PawnStatusEnum.LOCKED_IN_HOME) {
                        return true; //pionek "przed nami" jest już zablokowany, więc blokujemy swój
                    }
                }
            }
            return false;
        }
        return true; //w przypadku gdy wchodzimy na 4 pole końcowe (ostatnie)
    }

    public MoveTypeEnum checkField(Player currentPlayer, String field) {
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
