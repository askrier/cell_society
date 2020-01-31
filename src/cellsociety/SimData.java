package cellsociety;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimData {

    public static final String DATA_TYPE = "game";
    public static final List<String> DATA_FIELDS = List.of(
            "author",
            "cell_sides",
            "cell_rows",
            "num_cells",
            "row1_vals"
    );

    private String mySimType;
    private String myAuthor;
    private int myCellSides;
    private int myTotalCells;
    private List<List<Integer>> myCellVals;
    private Map<String, String> myDataValues;

    public SimData (String simType, String author, int cellSides, int totalCells, List<List<Integer>> cellVals) {
        mySimType = simType;
        myAuthor = author;
        myCellSides = cellSides;
        myTotalCells = totalCells;
        myCellVals = cellVals;
        myDataValues = new HashMap<>();
    }

    public String getSimType () { return mySimType; }

    public String getAuthor () { return myAuthor; }

    public int getShape () { return myCellSides; }

    public int getNumCells () { return myTotalCells; }

    public int getCellVal (int x_val, int y_val) {
        // TODO: IMPLEMENT RETURN THAT CHECKS FOR BOUNDS
        return 0;
    }

}
