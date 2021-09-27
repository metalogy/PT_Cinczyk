package GUI;

//import game.Game;

import game.*;
import game.GameController;
import game.Pawn;
import game.enums.*;
import game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Button endTurnButton;
    @FXML
    private Text rollResult;
    @FXML
    private Pane gamePane;
    @FXML
    private Button playButton;
    @FXML
    private Text playerTurn;
    @FXML
    private Text warning;
    @FXML
    private Circle turnCircle;

    private Scene gameScene;

    private boolean rollValidation = true;
    private boolean extraRoll = false;
    //private String gameID;
    private GameController gameController;
    private ArrayList<Circle> circles = new ArrayList<>(); //przechowuje okręgi - wizualizację pionków

    public GameBoardController(Game game) {
        this.gameController = new GameController(game);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("res/dice.png"), 70, 70, false, false);
        rollDiceButton.setGraphic(new ImageView(diceImage));
        Image hourglassImage = new Image(getClass().getResourceAsStream("res/hourglass.png"), 70, 70, false, false);
        endTurnButton.setGraphic(new ImageView(hourglassImage));
        Image playImage = new Image(getClass().getResourceAsStream("res/play.png"), 70, 70, false, false);
        playButton.setGraphic(new ImageView(playImage));


    }

    @FXML
    void endTurn() {
        this.rollValidation = true;
        this.gameController.nextPlayer();
        setPlayerTurnLabel(gameController);
    }

    @FXML
    void play() {
        playButton.setVisible(false);
        updateBoard();
        this.gameController.setGameStatus(GameStatusEnum.IN_PROGRESS);
        setPlayerTurnLabel(gameController);

        rollDiceButton.setOnAction(actionEvent -> {
            clearWarning();
            setPlayerTurnLabel(gameController);
            if (this.rollValidation) {
                this.rollValidation = false;
                int rolled = rollDice();
                if (rolled == 6) {
                    extraRoll = true;
                }
                clearWarning();

                gamePane.setOnMouseClicked(event -> {
                    clearWarning();
                    if (!this.rollValidation) {
                        if (event.getTarget() instanceof Circle) {
                            String clickedPawnID = (((Circle) event.getTarget()).getId());

                            String clickedPawnColour = clickedPawnID.substring(0, clickedPawnID.length() - 1);
                            int pawnID = Integer.parseInt(clickedPawnID.substring(clickedPawnID.length() - 1));

                            if (gameController.getCurrentPlayer().getPawnsColor().equals(clickedPawnColour)) {
                                if (move(gameController.getCurrentPlayer(), pawnID, rolled)) {
                                    if (gameController.checkWin(gameController.getCurrentPlayer())) {
                                        System.out.println("Player " + gameController.getCurrentPlayer().getPawnsColor()
                                                + " WON!");
                                        gameEnded(gameController.getCurrentPlayer());
                                    }
                                    if (!extraRoll) {
                                        gameController.nextPlayer();


                                    }
                                    this.extraRoll = false;
                                    this.rollValidation = true;

                                } else {
                                    setWarning("Can't move!");
                                }
                            } else {
                                setWarning("Wrong pawn!");

                            }
                        }
                    } else {
                        setWarning("You have to roll!");
                    }
                });
            } else {
                setWarning("Already rolled!");
            }
        });
    }

    private void setWarning(String message) {
        warning.setText(message);
    }

    private void clearWarning() {
        warning.setText("");
    }

    private void setPlayerTurnLabel(GameController gameController) {
        String label = "Player " + gameController.getCurrentPlayer().getLogin() + " turn!";
        playerTurn.setText(label);
        turnCircle.setFill(Color.valueOf(gameController.getCurrentPlayer().getPawnsColor().toLowerCase(Locale.ROOT)));
    }

    @FXML
    private Integer rollDice() {
        Integer rolled = Game.rollDice();
        this.rollResult.setText("Rolled: " + rolled);
        return rolled;
    }

    private boolean move(Player player, int pawnID, Integer rolled) {
        if (gameController.movePawn(player, pawnID, rolled)) {
            updateBoard();
            return true;
        }
        return false;
    }

    private void gameEnded(Player player) {
        this.gamePane.getChildren().clear();
        Text endText = new Text((this.gamePane.getWidth() / 2),
                this.gamePane.getHeight() / 2, "Player " + player.getLogin() + " has won!");
        this.gamePane.getChildren().add(endText);
        Button backToMenu = new Button("Back to menu");
        backToMenu.setLayoutX((this.gamePane.getWidth() / 2) - backToMenu.getWidth());
        backToMenu.setLayoutY((this.gamePane.getHeight() / 2) - 50);
        this.gamePane.getChildren().add(backToMenu);

        backToMenu.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerScreen.fxml"));
            PlayerScreen controller = new PlayerScreen();
            Parent gameParent = null;
            try {
                gameParent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene gameScene = new Scene(gameParent);
            Stage appStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            appStage.setScene(gameScene);
            appStage.show();

        });
    }

    private void updateBoard() {
        clearCircles();
        for (Player player : gameController.getPlayers()
        ) {
            int i = 0;
            for (Pawn pawn : player.getPawns()) {
                String pawnPosition = pawn.getPosition();
                Rectangle rec = (Rectangle) gamePane.getScene().lookup(pawnPosition);
                Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
                Circle circle = new Circle(boundsInScene.getCenterX(), boundsInScene.getCenterY(), 20.0f,
                        Color.valueOf(player.getPawnsColor().toLowerCase(Locale.ROOT)));
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(20.0f * 0.1);


                String pawnID = player.getPawnsColor() + i;
                i++;
                circle.setId(pawnID);
                circles.add(circle);
                gamePane.getChildren().add(circle);
            }
        }
    }

    private void clearCircles() {
        for (Circle circle : circles) {
            gamePane.getChildren().remove(circle);
        }
        circles.clear();
    }


}





