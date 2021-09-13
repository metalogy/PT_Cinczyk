package GUI;

import game.Game;
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
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Text  rollResult;
    @FXML
    private Pane gamePane;

    private Scene gameScene;


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
        Integer rolled= Game.rollDice();
        this.rollResult.setText("Rolled: "+rolled);
//        System.out.println(this.rollResult.getText());



        Rectangle rec = (Rectangle) gamePane.getScene().lookup("#homeRed_1");
        System.out.println(rec.getX() + rec.getY());

        Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
        System.out.println(boundsInScene.getCenterX() + boundsInScene.getCenterY());
        Circle circle = new Circle(boundsInScene.getCenterX(),boundsInScene.getCenterY(),20.0f, Color.ORANGE);
        gamePane.getChildren().add(circle);

    }



}
