package game;

import game.enums.GameStatusEnum;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private String gameID;
    private ArrayList<Player> players = new ArrayList<Player>();
    private GameStatusEnum gameStatus;


    //    private Player currentPlayer;
//    private int currentPlayerID;
    private Player winner;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameStatusEnum getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatusEnum gameStatus) {
        this.gameStatus = gameStatus;
    }


    public Game(String gameID, ArrayList<Player> players) {
        this.gameID = gameID;
        this.players = players;
        Collections.shuffle(this.players);
    }

    public static Integer rollDice() {
        int roll = (int) ((Math.random() * (6)) + 1);
        System.out.println("Roll: " + roll);
        return roll;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

}
