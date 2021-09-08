package GUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class LudoApp extends Application {
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 11;
    public static final int HEIGHT = 11;

    private Tile[][] board= new Tile[WIDTH][HEIGHT];
    private Group tileGroup=new Group();
    private Group pieceGroup=new Group();

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup,pieceGroup);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    Scene scene= new Scene(createContent());
    primaryStage.setTitle("Chińczyk");
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
