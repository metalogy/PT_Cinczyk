package GUI;

//import game.Game;

import game.Game;
import game.GameController;
import game.Pawn;
import game.enums.GameStatusEnum;
import game.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    private boolean rollValidation = true;
    private boolean extraRoll = false;

    //private String gameID;
    private GameController gameController;
    private ArrayList<Circle> circles = new ArrayList<Circle>(); //przechowuje okręgi - wizualizację pionków

    public GameBoardController(Game game) {
        this.gameController = new GameController(game);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("dice.png"), 70, 70, false, false);
        rollDiceButton.setGraphic(new ImageView(diceImage));
    }

    @FXML
    void play() {
        updateBoard();
        gameController.setGameStatus(GameStatusEnum.IN_PROGRESS);

        rollDiceButton.setOnAction(actionEvent -> {
            if (this.rollValidation) {
                this.rollValidation = false;
                int rolled = rollDice();
                if (rolled == 6) {
                    extraRoll = true;
                }
                //rollDiceButton.setDisable(ture);
                //TODO zablokowanie wielokrotnego wciśnięcia rolowania
                gamePane.setOnMouseClicked(event -> {
                    if (!this.rollValidation) {
                        if (event.getTarget() instanceof Circle) {
                            String clickedPawnID = (((Circle) event.getTarget()).getId());

                            String clickedPawnColour = clickedPawnID.substring(0, clickedPawnID.length() - 1);
                            int pawnID = Integer.parseInt(clickedPawnID.substring(clickedPawnID.length() - 1));

                            if (gameController.getCurrentPlayer().getPawnsColor().equals(clickedPawnColour)) {
                                move(gameController.getCurrentPlayer(), pawnID, rolled);
                                if (true) {
                                //if (gameController.checkWin(gameController.getCurrentPlayer())) {
                                    System.out.println("Player " + gameController.getCurrentPlayer().getPawnsColor()
                                            + " WIN!");
                                    gameEnded(gameController.getCurrentPlayer());
                                }
                                if (!extraRoll) {
                                    gameController.nextPlayer(); //#TODO wciskanie bez rollowania

                                }
                                this.extraRoll = false;
                                this.rollValidation = true;

                            } else {
                                System.out.println("Zły pionek!"); //#TODO jakiś komunikat w GUI
                            }
                        }
                    }
                });
            }
        });
    }

    @FXML
    private Integer rollDice() {
        Integer rolled = Game.rollDice();
        this.rollResult.setText("Rolled: " + rolled);
        return rolled;
    }

    private void move(Player player, int pawnID, Integer rolled) {

        boolean movable = gameController.movePawn(player, pawnID, rolled);

        if (movable) {
            updateBoard();
        }
    }
    private void gameEnded(Player player)
    {
        this.gamePane.getChildren().clear();
        Text endText= new Text(this.gamePane.getWidth()/2,this.gamePane.getHeight()/2,"Player "+player.getLogin()+" has won!");
        this.gamePane.getChildren().add(endText);
        Button backToMenu = new Button("Back to menu");
        backToMenu.setOnMouseClicked(mouseEvent ->
                );
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


                String pawnID = player.getPawnsColor().toString() + i;
                i++;
                circle.setId(pawnID);
                circles.add(circle);
                gamePane.getChildren().add(circle);
            }
            i = 0;

        }
    }

    private void clearCircles() {
        for (Circle circle : circles) {
            gamePane.getChildren().remove(circle);
        }
        circles.clear();
    }


}





