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
    private void toGame(ActionEvent event) throws IOException {
        //#TODO menu wyboru graczy
        Player player = new Player(PawnsColorEnum.Red, "test");
        Player player2 = new Player(PawnsColorEnum.Blue, "test2");
        ArrayList<Player> playerArrayList = new ArrayList<Player>();
        playerArrayList.add(player);
        playerArrayList.add(player2);
//        Player player=new Player(PawnsColorEnum.Red,"test");
//        ArrayList<Player> playerArrayList=new ArrayList<Player>();
//        playerArrayList.add(player);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        Game game = new Game("TestGame",playerArrayList);
        GameBoardController controller=new GameBoardController(game);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerScreen.fxml"));
//       GameBoardController controller=new GameBoardController("test",playerArrayList);
        //PlayerScreen controller = new PlayerScreen();
        loader.setController(controller);
        Parent gameParent = loader.load();

        Scene gameScene = new Scene(gameParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(gameScene);
        appStage.show();


    }


}
