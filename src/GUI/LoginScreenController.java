package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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



        Rectangle rec =(Rectangle) gameScene.lookup("#homeRed_1");
        rec.setFill(Color.BLACK);
        System.out.println(rec.getX() + rec.getY());

        Bounds boundsInScene = rec.localToScene(rec.getBoundsInLocal());
        System.out.println(boundsInScene.getCenterX() + boundsInScene.getCenterY());
        Circle circle = new Circle(boundsInScene.getCenterX(),boundsInScene.getCenterY(),100.0f,Color.ORANGE);
        //pane.getChildren().add(circle);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.show();

    }


}
