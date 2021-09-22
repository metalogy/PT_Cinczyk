package game;

import GUI.GameBoardController;

import java.util.ArrayList;

public class Validation {

    private Player presentPlayer;
    private Integer presentPawnId;

    Validation(){
        System.out.println("Validation service created\n");
    }

    public Code validate(Player player, Integer id, Integer value){
        setPresentPlayer(player);
        setPresentPawnId(id);
        ArrayList<Player> playersArray = GameBoardController.getPlayers();
        ArrayList<Code> codesArray = new ArrayList<>();
        for (Player players : playersArray) {
            codesArray.add(checkPlayer(players, value));
        }
        return (codesArray.isEmpty() ? formResponse(codesArray): null);
    }

    private Code formResponse(ArrayList<Code> arrayList) {
        boolean check = true;
        Code condition = arrayList.get(0);
        for (Code code : arrayList){
            if (code != condition) check = false;
        }
        return (check ? condition : Code.ERROR);
    }

    private Code checkPlayer(Player player, Integer value) {
        //todo sprawdzenie de facto
        return null;
    }

    public Player getPresentPlayer() {
        return presentPlayer;
    }

    public void setPresentPlayer(Player presentPlayer) {
        this.presentPlayer = presentPlayer;
    }

    public Integer getPresentPawnId() {
        return presentPawnId;
    }

    public void setPresentPawnId(Integer presentPawnId) {
        this.presentPawnId = presentPawnId;
    }
}
