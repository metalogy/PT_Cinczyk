package GUI;

import game.Game;
import game.enums.PawnsColorEnum;
import game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginScreenController {
    @FXML
    private Button loginButton;
    @FXML
    private Button guestButton;

    @FXML
    private void toPlayerScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerScreen.fxml"));
        Parent gameParent = loader.load();
        Scene gameScene = new Scene(gameParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.centerOnScreen();
        appStage.show();


    }


}
