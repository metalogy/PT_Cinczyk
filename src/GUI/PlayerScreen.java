package GUI;

import game.Game;
import game.enums.PawnsColorEnum;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private Text Warning;
    @FXML
    private Button startButton;
    private ArrayList<ChoiceBox<String>>Choiceboxes;
    private Game game;
    private ArrayList<TextField>TextFields;

    private String[] colour={"red","blue","yellow","green"};

//    public PlayerScreen(Game game) {
//        this.game=game;
//    }
public PlayerScreen() {

}
    @FXML
    private void startGame(ActionEvent event) throws IOException {
    Choiceboxes=new ArrayList<ChoiceBox<String>>();
    TextFields=new ArrayList<TextField>();
    Choiceboxes.add(choicebox1);
    Choiceboxes.add(choicebox2);
    Choiceboxes.add(choicebox3);
    Choiceboxes.add(choicebox4);
    TextFields.add(textField1);
    TextFields.add(textField2);
    TextFields.add(textField3);
    TextFields.add(textField4);
        int red=0,blue = 0,yellow=0,green=0;
        for(ChoiceBox<String>choicebox : Choiceboxes){
            switch (choicebox.getValue()){
                    case "red":
                    red+=1;
                    break;
                    case "green":
                    green+=1;
                    break;
                    case "blue":
                    blue+=1;
                    break;
                    case "yellow":
                    yellow+=1;
                    break;
                    default:
                    break;
            }
        }
        if(red<2&&blue<2&&green<2&&yellow<2){
            ArrayList<Player> playerArrayList=new ArrayList<Player>();
            int i =0;
            for(ChoiceBox<String>choicebox : Choiceboxes){
                if(!Objects.equals(choicebox.getValue(), "None")) {
                    Player player= new Player(PawnsColorEnum.Red, TextFields.get(i).getText());
                    switch (choicebox.getValue()) {
                        case "red":
                            player = new Player(PawnsColorEnum.Red, TextFields.get(i).getText());
                            break;
                        case "green":
                            player = new Player(PawnsColorEnum.Green, TextFields.get(i).getText());
                            break;
                        case "blue":
                            player = new Player(PawnsColorEnum.Blue, TextFields.get(i).getText());
                            break;
                        case "yellow":
                            player = new Player(PawnsColorEnum.Yellow, TextFields.get(i).getText());
                            break;
                        default:
                            break;
                    }

                    i++;
                    playerArrayList.add(player);
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            Game game = new Game("TestGame",playerArrayList);
            GameBoardController controller=new GameBoardController(game);
            loader.setController(controller);
            Parent gameParent = loader.load();
            Scene gameScene = new Scene(gameParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(gameScene);
            appStage.show();



        }else Warning.setVisible(true);


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox1.getItems().addAll(colour);
        choicebox2.getItems().addAll(colour);
        choicebox3.getItems().addAll(colour);
        choicebox4.getItems().addAll(colour);

}}
