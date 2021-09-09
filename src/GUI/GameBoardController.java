package GUI;

import game.GamePlay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    private Button rollDiceButton;
    @FXML
    private Text  rollResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image diceImage = new Image(getClass().getResourceAsStream("dice.png"),70,70,false,false);
        ImageView imageView = new ImageView(diceImage);
        //ImageView imageView = new ImageView("dice.png");
        rollDiceButton.setGraphic(new ImageView(diceImage));
        //rollDiceButton.setGraphic(imageView);

    }

    @FXML
    private void rollDice(){
        Integer rolled=GamePlay.rollDice();
        this.rollResult.setText("Rolled: "+rolled);
//        System.out.println(this.rollResult.getText());

        //rollResult.setText("aaaaaaa");
        //rollResult.setVisible(true);

    }



}
