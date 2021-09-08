package GUI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Tile extends Rectangle{

    private Piece piece;

    public boolean hasPiece(){
        return piece !=null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Tile(ColorEnum color,int x, int y){
        setWidth(LudoApp.TILE_SIZE);
        setHeight(LudoApp.TILE_SIZE);

        relocate(x*LudoApp.TILE_SIZE,y*LudoApp.TILE_SIZE);

        setFill(color.equals(ColorEnum.BLUE)?Color.BLUE:color.equals(ColorEnum.GREEN)?Color.GREEN:color.equals(ColorEnum.RED)?Color.RED:color.equals(ColorEnum.YELLOW)?Color.YELLOW:color.equals(ColorEnum.LBLUE)?Color.LIGHTBLUE:color.equals(ColorEnum.LGREEN)?Color.LIGHTGREEN:color.equals(ColorEnum.LRED)?Color.PINK:color.equals(ColorEnum.LYELLOW)?Color.LIGHTYELLOW:Color.WHITE);

    }
}
