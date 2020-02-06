package XML;

import java.util.*;

public class SimData {

    public static final String DATA_TYPE = "simulation";
    public static final List<String> DATA_FIELDS = List.of(
            "author",
            "sim_type",
            "cell_sides",
            "cell_rows",
            "cell_columns",
            "spread_probability",
            "vals"
    );

    private static final int AUTHOR_INDEX = 0;
    private static final int SIM_TYPE_INDEX = 1;
    private static final int CELL_SIDES_INDEX = 2;
    private static final int CELL_ROWS_INDEX = 3;
    private static final int CELL_COLUMNS_INDEX = 4;
    private static final int SPREAD_PROBABILITY_INDEX = 5;
    private static final int VALS_INDEX = 6;

    private String mySimType;
    private String myAuthor;
    private int myCellSides;
    private int myCellRows;
    private int myCellColumns;
    private String myVals;
    private double mySpreadProb;
    private List<List<Integer>> myCellVals;
//    private Map<String, String> myDataValues;

    public SimData (String author, String simType, int cellSides, int cellRows, int cellColumns, String vals, double spreadProb) {
        mySimType = simType;
        myAuthor = author;
        myCellSides = cellSides;
        myCellRows = cellRows;
        myCellColumns = cellColumns;
        myVals = vals;
        mySpreadProb = spreadProb;
//        myDataValues = new HashMap<>();
    }

    public SimData (Map<String, String> dataValues) {
        this(dataValues.get(DATA_FIELDS.get(AUTHOR_INDEX)),
                dataValues.get(DATA_FIELDS.get(SIM_TYPE_INDEX)),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_SIDES_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_ROWS_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(CELL_COLUMNS_INDEX))),
                dataValues.get(DATA_FIELDS.get(VALS_INDEX)),
                Float.parseFloat(dataValues.get(DATA_FIELDS.get(SPREAD_PROBABILITY_INDEX))));
        setVals();
//        myDataValues = dataValues;
    }

    public String getSimType () { return mySimType; }

    public String getAuthor () { return myAuthor; }
/*
    public int getShape () { return myCellSides; }

 */
    public int getRows () { return myCellRows; }

    public int getColumns () { return myCellColumns; }

    public double getSpreadProb () { return mySpreadProb; }

    public List<List<Integer>> getValList () { return myCellVals; }
/*
    public int getCellVal (int x_val, int y_val) {

        if(outOfBounds(x_val, y_val)) return -1;

        return myCellVals.get(y_val).get(x_val);

    }

    public boolean outOfBounds(int x_val, int y_val) {

        return (x_val < 0 || x_val >= myCellColumns || y_val < 0 || y_val >= myCellRows);

    }

 */

    private void setVals () {
        int temp;
        myCellVals = new ArrayList<>();

        for(int i = 0; i < myCellRows; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < myCellColumns; j++) {
                temp = Integer.parseInt(myVals.substring(0, 1));
                row.add(temp);
                myVals = myVals.substring(1);
            }
            myCellVals.add(row);
        }
    }

}
