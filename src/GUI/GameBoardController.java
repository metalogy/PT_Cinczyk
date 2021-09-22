package GUI;

//import game.Game;

import game.Game;
import game.enums.GameStatusEnum;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Text rollResult;
    @FXML
    private Pane gamePane;

    private Scene gameScene;

    private int rollValidation = 1;

    private String gameID;
    private Game game;
    private ArrayList<Circle> circles = new ArrayList<Circle>(); //przechowuje okręgi - wizualizację pionków

    public GameBoardController(String gameID, ArrayList<Player> players) {
        this.gameID = gameID;
        this.game = new Game(gameID, players);
    }

    private void initializePawns() {
        for (Player player : game.getPlayers()
        ) {
            for (int i = 0; i < 4; i++) {
                String startingPosition = "#home" + player.getPawnsColor() + "_" + i;
                Rectangle rec = (Rectangle) gamePane.getScene().lookup(startingPosition);
                Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
                Circle circle = new Circle(boundsInScene.getCenterX(), boundsInScene.getCenterY(), 20.0f,
                        Color.valueOf(player.getPawnsColor().toLowerCase(Locale.ROOT)));
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(20.0f * 0.1);


                String pawnID = player.getPawnsColor().toString() + i;
                circle.setId(pawnID);
                circles.add(circle);
                gamePane.getChildren().add(circle);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("dice.png"), 70, 70, false, false);
        rollDiceButton.setGraphic(new ImageView(diceImage));
    }

    @FXML
    void play() {
        initializePawns();

        game.setGameStatus(GameStatusEnum.IN_PROGRESS);

        rollDiceButton.setOnAction(actionEvent -> {
            if(this.rollValidation == 1){
                this.rollValidation = 0;
                int rolled = rollDice();
                //TODO zablokowanie wielokrotnego wciśnięcia rolowania
                gamePane.setOnMouseClicked(event -> {
                    if (event.getTarget() instanceof Circle) {
                        String clickedPawnID = (((Circle) event.getTarget()).getId());

                        String clickedPawnColour = clickedPawnID.substring(0, clickedPawnID.length() - 1);
                        int pawnID = Integer.parseInt(clickedPawnID.substring(clickedPawnID.length() - 1));

                        if (game.getCurrentPlayer().getPawnsColor().equals(clickedPawnColour)) {
                            move(game.getCurrentPlayer(), pawnID, rolled);
                            this.rollValidation = 1;
                            game.nextPlayer(); //#TODO wciskanie bez rollowania

                    } else {
                        System.out.println("Zły pionek!"); //#TODO jakiś komunikat w GUI
                    }
                }
            });}
        });
    }

    @FXML
    private Integer rollDice() {
        Integer rolled = Game.rollDice();
        this.rollResult.setText("Rolled: " + rolled);
        return rolled;
    }

    private void move(Player player, int pawnID, Integer rolled) {

        boolean movable = player.movePawn(pawnID, rolled);

        if (movable) {
            Rectangle newPawnRec = (Rectangle) gamePane.getScene().lookup(player.getPawns()[pawnID].getPosition());
            System.out.println(player.getPawns()[pawnID].getPosition());
            Bounds boundsInScene = newPawnRec.localToScene(newPawnRec.getBoundsInLocal());

            int playerIndex = game.getPlayers().indexOf(player);
            if (playerIndex > 0) {
                circles.get(playerIndex * 4 + pawnID).relocate(newPawnRec.getLayoutX(), newPawnRec.getLayoutY());
            } else {
                circles.get(pawnID).relocate(newPawnRec.getLayoutX(), newPawnRec.getLayoutY());
            }
        }
    }

}





