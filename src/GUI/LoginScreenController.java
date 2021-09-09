package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {
    @FXML
    private Button loginButton;
    @FXML
    private Button guestButton;

    @FXML
    private void toGame(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        Scene gameScene = new Scene(gameParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.show();

        //tutaj obsługa gry
    }


}
