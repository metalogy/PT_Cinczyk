package GUI;

import game.PawnsColorEnum;
import game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerScreen implements Initializable {

    @FXML
    private ChoiceBox<String>choicebox1;
    @FXML
    private ChoiceBox<String>choicebox2;
    @FXML
    private ChoiceBox<String>choicebox3;
    @FXML
    private ChoiceBox<String>choicebox4;
    @FXML
    private ChoiceBox<String>choicebox5;
    @FXML
    private ChoiceBox<String>choicebox6;
    @FXML
    private ChoiceBox<String>choicebox7;
    @FXML
    private ChoiceBox<String>choicebox8;
    @FXML
    private Button startButton;

    private String[] player={"player1","player2","player3","player4"};
    private String[] colour={"red","blue","yellow","green"};

    public PlayerScreen() {
    }
    @FXML
    private void startGame(ActionEvent event) throws IOException {
        Player player=new Player(PawnsColorEnum.Red,"test");
        ArrayList<Player> playerArrayList=new ArrayList<Player>();
        playerArrayList.add(player);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        GameBoardController controller=new GameBoardController("test",playerArrayList);
        loader.setController(controller);
        Parent gameParent = loader.load();
        Scene gameScene = new Scene(gameParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox1.getItems().addAll(player);
        choicebox2.getItems().addAll(player);
        choicebox3.getItems().addAll(player);
        choicebox4.getItems().addAll(player);
        choicebox5.getItems().addAll(colour);
        choicebox6.getItems().addAll(colour);
        choicebox7.getItems().addAll(colour);
        choicebox8.getItems().addAll(colour);
    }
}
