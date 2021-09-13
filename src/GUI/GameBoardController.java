package GUI;

//import game.Game;
import game.GameStatusEnum;
import game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Text  rollResult;
    @FXML
    private Pane gamePane;

    private Scene gameScene;

    private String gameID;
    private ArrayList<Player> players = new ArrayList<Player>();
    private GameStatusEnum gameStatus;
    private Player winner;

    public GameBoardController(String gameID, ArrayList<Player> players) {
        this.gameID = gameID;
        this.players = players;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("dice.png"),70,70,false,false);
        ImageView imageView = new ImageView(diceImage);
        //ImageView imageView = new ImageView("dice.png");
        rollDiceButton.setGraphic(new ImageView(diceImage));
//        this.gameScene = gamePane.getScene();
    }

    @FXML
    private void rollDice(){
        Integer rolled= (int) ((Math.random() * (6)) + 1);
        this.rollResult.setText("Rolled: "+rolled);
//        System.out.println(this.rollResult.getText());



        //Rectangle rec = (Rectangle) gamePane.getScene().lookup("#homeRed_1");
        Rectangle rec = (Rectangle) gamePane.getScene().lookup("0");
        System.out.println(rec.getX() + rec.getY());

        Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
        System.out.println(boundsInScene.getCenterX() + boundsInScene.getCenterY());
        Circle circle = new Circle(boundsInScene.getCenterX(),boundsInScene.getCenterY(),20.0f, Color.ORANGE);
        gamePane.getChildren().add(circle);
        System.out.println(players.get(0).getLogin());

    }
    private void move(Player player, int pawnID, Integer rolled)
    {

    }



}
