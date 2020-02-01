package cellsociety;

import java.util.ArrayList;
import java.lang.reflect.Method;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {
    ArrayList<Cell> myArrayOfCells = new ArrayList<Cell>();
    public boolean exists;
    public boolean update;
    public Rectangle r;
    public int c;
    public int g;
    public Grid(int height, int width, int vCellNum, int hCellNum, String gameVariation){
        exists = true;
        r = new Rectangle(50,5,500,500);
        r.setFill(Color.LIGHTCORAL);
        update = true;
         c =0;
         g =0;
    }
    public void update(){


        if(update ==true){
            c+=1;
            g+=1;
            r.setFill(Color.rgb(c,g,4));
            if(c ==255){
                update = false;
            }
    }}
}
