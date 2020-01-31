package cellsociety;

import java.util.ArrayList;
import java.lang.reflect.Method;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {
    ArrayList<Cell> myArrayOfCells = new ArrayList<Cell>();
    public boolean exists;
    public Rectangle r;
    public Grid(int height, int width, int vCellNum, int hCellNum, String gameVariation){
        exists = true;
        r = new Rectangle(500,500,500,500);
        r.setFill(Color.LIGHTCORAL);

    }
}
