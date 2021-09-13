package GUI;

//import game.Game;

import game.GameStatusEnum;
import game.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Text rollResult;
    @FXML
    private Pane gamePane;

    private Scene gameScene;

    private String gameID;
    private ArrayList<Player> players = new ArrayList<Player>();
    private GameStatusEnum gameStatus;
    private Player winner;

    private ArrayList<Circle> circles = new ArrayList<Circle>(); //przechowuje wizualizację pionków, ogranąć kolejność? jakie do niebieskiego, jakie do zieloneog

    public GameBoardController(String gameID, ArrayList<Player> players) {
        this.gameID = gameID;
        this.players = players;

    }

    private void initializePawns() {
        for (Player player : players
        ) {
            for (int i = 0; i < 4; i++) {
                String startingPosition = "#home" + player.getPawnsColor() + "_" + i;
                Rectangle rec = (Rectangle) gamePane.getScene().lookup(startingPosition);
                Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
                Circle circle = new Circle(boundsInScene.getCenterX(), boundsInScene.getCenterY(), 20.0f, player.getPawnsColor().getValue()); //#TODO nie działa kolor
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(20.0f * 0.03);
                circles.add(circle);
                gamePane.getChildren().add(circle);

            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("dice.png"), 70, 70, false, false);
        ImageView imageView = new ImageView(diceImage);
        //ImageView imageView = new ImageView("dice.png");
        rollDiceButton.setGraphic(new ImageView(diceImage));
//        this.gameScene = gamePane.getScene();
        //initializePawns(); #TODO znaleźc miejsce do wywołania - przycisk PLAY?
    }

    @FXML
    void play() {
        initializePawns();
        //PEWNIE GŁOWNA PETLA

        //TESTOWO DO MOVE
        Rectangle newPawnRec = (Rectangle) gamePane.getScene().lookup("#0"); //#TODO enum z pozycjami startowymi pionków
        Bounds boundsInScene = newPawnRec.localToScene(newPawnRec.getBoundsInLocal());
        circles.get(0).relocate(boundsInScene.getCenterX(), boundsInScene.getCenterY());


    }

    @FXML
    private void rollDice() {
        Integer rolled = (int) ((Math.random() * (6)) + 1);
        this.rollResult.setText("Rolled: " + rolled);

        Player player = players.get(0);
        move(player, 0, rolled);


//        //Rectangle rec = (Rectangle) gamePane.getScene().lookup("#homeRed_1");
//        Rectangle rec = (Rectangle) gamePane.getScene().lookup("#0");
//        System.out.println(rec.getX() + rec.getY());
//
//        Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
//        System.out.println(boundsInScene.getCenterX() + boundsInScene.getCenterY());
//        Circle circle = new Circle(boundsInScene.getCenterX(), boundsInScene.getCenterY(), 20.0f, Color.ORANGE);
//        gamePane.getChildren().add(circle);
//        System.out.println(players.get(0).getLogin());


    }

    private void move(Player player, int pawnID, Integer rolled) {
        //#TODO wyjście z bazy + domki
        player.movePawn(pawnID, rolled);

        Rectangle newPawnRec = (Rectangle) gamePane.getScene().lookup(player.getPawns()[pawnID].getPosition());
        Bounds boundsInScene = newPawnRec.localToScene(newPawnRec.getBoundsInLocal());

        int playerIndex = players.indexOf(player);
        if (playerIndex == 1) {
            circles.get(playerIndex * 4 + pawnID).relocate(boundsInScene.getCenterX(), boundsInScene.getCenterY());
        } else {
            circles.get(pawnID).relocate(boundsInScene.getCenterX(), boundsInScene.getCenterY());
        }
    }
    //#TODO przechowywanie okręgów i aktualizacja ich pozycji


}





