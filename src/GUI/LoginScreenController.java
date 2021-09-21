package GUI;

import game.PawnsColorEnum;
import game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginScreenController {
    @FXML
    private Button loginButton;
    @FXML
    private Button guestButton;

    @FXML
    private void toGame(ActionEvent event) throws IOException {
        Player player=new Player(PawnsColorEnum.Red,"test");
        Player player2=new Player(PawnsColorEnum.Blue,"test2");
        ArrayList<Player> playerArrayList=new ArrayList<Player>();
        playerArrayList.add(player);
        playerArrayList.add(player2);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        GameBoardController controller=new GameBoardController("test",playerArrayList);
        loader.setController(controller);
        Parent gameParent = loader.load();

        Scene gameScene = new Scene(gameParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.show();



    }


}
