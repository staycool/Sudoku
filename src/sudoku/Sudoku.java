/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author KID
 */
public class Sudoku {

    private final int ROW_COUNT = 9;
    private final int COLUMN_COUNT = 9;
    private final int BOX_COUNT = 9;

    public Sudoku() {
        //this.rawUnit = new int[][]{{1, 5, 7, 0, 0, 9, 0, 0, 2}, {8, 0, 0, 5, 0, 7, 0, 9, 0}, {4, 9, 6, 0, 0, 3, 0, 0, 0}, {0, 0, 2, 9, 0, 0, 5, 6, 3}, {3, 8, 1, 7, 5, 6, 2, 4, 9}, {5, 0, 9, 2, 0, 4, 0, 0, 1}, {9, 1, 4, 3, 7, 0, 6, 2, 8}, {0, 7, 5, 0, 0, 0, 3, 1, 4}, {0, 3, 8, 1, 4, 2, 9, 7, 0}};
    }

    public void run() {
        init();
        long startTime = System.currentTimeMillis();
        boolean succ = calc(0, -1);
        //boolean succ = calcByd(0);
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (float) (startTime - endTime) / 1000 + "s");
        System.out.println(succ);
        printSudoku();
    }

    public boolean calc(int indexX, int indexY) {
        //索引位于一行的最后一列,则初始索引重定位在下一行第一列
        for (int i = (indexY == COLUMN_COUNT - 1 ? indexX + 1 : indexX); i < ROW_COUNT; i++) {

            for (int j = (i == indexX ? indexY + 1 : 0); j < COLUMN_COUNT; j++) {
                //int currentValueIndexX, currentValueIndexY;
                // current unit is blank
                if (rawUnit[i][j] == 0) {
                    int a;
                    while ((a = getCorrectValue(i, j)) != -1) {
                        unit[i][j] = a;
                        if (calc(i, j)) {
                            return true;
                        }
                    }
                    if (a == -1) {
                        unit[i][j] = 0;
                        return false;
                    }
                }
            }
        }
        //全部遍历完成
        return true;
    }

    public boolean calcByd(int kk) {
        //if (density == null) {
        //    return false;
        //}

        //kk++;
        System.out.println("kk: " + kk);
        if (kk >= density.length) {
            return true;
        }
        int i;
        int j;
        i = density[kk].getmIndexX();
        j = density[kk].getmIndexY();
        if (rawUnit[i][j] == 0) {
            int a;
            while ((a = getCorrectValue(i, j)) != -1) {
                unit[i][j] = a;
                if (calcByd(kk + 1)) {
                    return true;
                }
            }
            if (a == -1) {
                return false;
            }
        }

        //全部遍历完成
        return true;
    }
    /*int rawUnit[][]
            = new int[][]{
                {1, 5, 7, 0, 0, 9, 0, 0, 2}, 
                {8, 0, 0, 5, 0, 7, 0, 9, 0}, 
                {4, 9, 6, 0, 0, 3, 0, 0, 0}, 
                {0, 0, 2, 9, 0, 0, 5, 6, 3}, 
                {3, 8, 1, 7, 5, 6, 2, 4, 9}, 
                {5, 0, 9, 2, 0, 4, 0, 0, 1}, 
                {9, 1, 4, 3, 7, 0, 6, 2, 8}, 
                {0, 7, 5, 0, 0, 0, 3, 1, 4}, 
                {0, 3, 8, 1, 4, 2, 9, 7, 0}};
    */
    
    int rawUnit[][] = new int[][]{
    {0,0,4,9,1,6,2,3,5},
    {6,3,5,2,4,8,9,7,1},
    {0,0,0,7,3,5,4,8,6},
    {0,0,3,8,0,2,0,4,7},
    {4,0,8,0,0,0,0,5,2},
    {0,0,2,1,0,4,0,0,0},
    {1,4,0,5,0,0,0,0,8},
    {0,0,6,4,2,0,0,1,9},
    {0,0,0,0,8,1,0,0,4}};
    /*int rawUnit[][] = new int[][]{
    {0,0,4,9,1,6,2,3,5},
    {6,3,5,2,4,8,9,7,1},
    {0,0,1,7,3,5,4,8,6},
    {0,0,3,8,0,2,0,4,7},
    {4,0,8,6,0,0,0,5,2},
    {0,0,2,1,0,4,8,9,3},
    {1,4,0,5,6,0,0,2,8},
    {0,0,6,4,2,0,0,1,9},
    {0,0,0,3,8,1,0,6,4}
};*/
    

    //进行完善(填数字)的数独数据
    int unit[][];

    //密度值 0~24
    //横向,纵向,九宫格的密度值 0~8
    int d[][];
    ArrayList<Density> densitys;
    Density density[];
    int size;

    public void init() {
        //rawUnit = new int[9][9];
        unit = rawUnit;
        initDensityArray();
        printArray(d, ROW_COUNT, COLUMN_COUNT);
        printSudoku();

        c();
    }

    public void c() {
        //Java7之前API,
        //ArrayList<Density> densitys = new ArrayList<Density>();

        densitys = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (d[i][j] != -1) {
                    densitys.add(new Density(i, j, d[i][j]));
                }
            }
        }
        Collections.sort(densitys);
        System.out.printf("Original  sort, list:%s\n", densitys);

        System.out.println("size" + densitys.size());
        size = densitys.size();
        //density = (Density[]) densitys.toArray();
        density = new Density[densitys.size()];
        densitys.toArray(density);

    }

    public void initDensityArray() {
        int rowNum[] = new int[ROW_COUNT];
        int columnNum[] = new int[COLUMN_COUNT];
        int boxNum[] = new int[BOX_COUNT];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (rawUnit[i][j] != 0) {
                    rowNum[i]++;
                }

                if (rawUnit[j][i] != 0) {
                    columnNum[i]++;
                }

                if (rawUnit[i][j] != 0) {
                    /*
                        0   1   2
                        3   4   5
                        6   7   8
                     */
                    boxNum[(i / 3) * 3 + j / 3]++;
                }
            }
        }

        //initialize array d
        d = new int[9][9];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (rawUnit[i][j] != 0) {
                    d[i][j] = -1;
                } else {
                    d[i][j] = rowNum[i] + columnNum[j] + boxNum[(i / 3) * 3 + j / 3];
                }
            }
        }
    }

    public void printSudoku() {
        printArray(unit, ROW_COUNT, COLUMN_COUNT);
    }

    //Basic print function,used to print rowXcol array.
    public void printArray(int array[][], int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * set one value from one row(行)
     *
     * @param indexX
     * @param indexY
     * @return
     */
    public int getValueFromOneRow(int indexX, int indexY) {
        return getValueFromOneRow(indexX, indexY, unit[indexX][indexY]);
    }

    /**
     * set one value from one row(行)
     *
     * @param indexX
     * @param indexY
     * @param startValue
     * @return
     */
    public int getValueFromOneRow(int indexX, int indexY, int startValue) {
        int a = startValue;
        //validate the value
        if (!(0 <= a && a < 9)) {
            return -1;
        }

        boolean hasEqualValue = true;
        do {
            a = a + 1;
            int num = 0;
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (j != indexY) {
                    if (a == unit[indexX][j]) {
                        break;
                    } else {
                        num++;
                    }
                }
            }
            //遍历整行,没有相等值
            if (num == 8) {
                hasEqualValue = false;
            }
        } while (a < 9 && hasEqualValue);

        if (!hasEqualValue) {
            return a;
        } else {
            return -1;
        }
    }

    /**
     * set one value from one column(列)
     *
     * @param indexX
     * @param indexY
     * @return
     */
    public int getValueFromOneColumn(int indexX, int indexY) {
        return getValueFromOneColumn(indexX, indexY, unit[indexX][indexY]);
    }

    /**
     * set one value from one column(列)
     *
     * @param indexX
     * @param indexY
     * @param startValue
     * @return
     */
    public int getValueFromOneColumn(int indexX, int indexY, int startValue) {
        int a = startValue;
        //validate the value
        if (!(0 <= a && a < 9)) {
            return -1;
        }

        boolean hasEqualValue = true;
        do {
            a = a + 1;
            int num = 0;
            for (int i = 0; i < ROW_COUNT; i++) {
                if (i != indexX) {
                    if (a == unit[i][indexY]) {
                        break;
                    } else {
                        num++;
                    }
                }
            }
            //遍历整列,没有相等值
            if (num == 8) {
                hasEqualValue = false;
            }
        } while (a < 9 && hasEqualValue);
        if (!hasEqualValue) {
            return a;
        } else {
            return -1;
        }
    }

    /**
     * set one value from one box(小九宫)
     *
     * @param indexX
     * @param indexY
     * @return
     */
    public int getValueFromOneBox(int indexX, int indexY) {
        return getValueFromOneBox(indexX, indexY, unit[indexX][indexY]);
    }

    /**
     * set one value from one box(小九宫)
     *
     * @param indexX
     * @param indexY
     * @param startValue
     * @return
     */
    public int getValueFromOneBox(int indexX, int indexY, int startValue) {
        int a = startValue;
        //validate the value
        if (!(0 <= a && a < 9)) {
            return -1;
        }

        boolean hasEqualValue = true;

        do {
            a = a + 1;
            int num = 0;
            for (int i = (indexX / 3) * 3; i < 3 + (indexX / 3) * 3; i++) {
                for (int j = (indexY / 3) * 3; j < 3 + (indexY / 3) * 3; j++) {
                    if (indexX != i || indexY != j) {
                        if (a == unit[i][j]) {
                            //跳出双重循环
                            i = 3 + (indexX / 3) * 3;
                            j = 3 + (indexY / 3) * 3;
                        } /*else if (i == 2 + (indexX/3) * 3 && j == 2 + (indexY/3) * 3) {
                            //全部遍历,没有相同值
                            hasEqualValue = false;
                        }*/ else {
                            num++;
                        }
                    }
                }
            }
            //全部遍历,没有相同值
            if (num == 8) {
                hasEqualValue = false;
            }
        } while (a < 9 && hasEqualValue);
        if (!hasEqualValue) {
            return a;
        } else {
            return -1;
        }
    }

    /**
     * validate value
     *
     * @param rowValue
     * @param columnValue
     * @param boxValue
     * @return
     */
    public boolean isValidatedValue(int rowValue, int columnValue, int boxValue) {
        return rowValue == columnValue
                && columnValue == boxValue
                && 0 < rowValue
                && rowValue <= 9;
    }

    public int getCorrectValue(int indexX, int indexY) {
        int rowValue = getValueFromOneRow(indexX, indexY);
        int columnValue = getValueFromOneColumn(indexX, indexY);
        int boxValue = getValueFromOneBox(indexX, indexY);

        if (rowValue == -1 || columnValue == -1 || boxValue == -1) {
            return -1;
        }
        
        //bubble 
        //a>=b; b>=c; c>=a; => a==b==c
        while (!isValidatedValue(rowValue, columnValue, boxValue)) {
            if (rowValue < columnValue) {
                int nextRowValue = getValueFromOneRow(indexX, indexY, rowValue);
                if (nextRowValue == -1) {
                    return -1;
                } else {
                    rowValue = nextRowValue;
                }
            }
            if (columnValue < boxValue) {
                int nextColumnValue = getValueFromOneColumn(indexX, indexY, columnValue);
                if (nextColumnValue == -1) {
                    return -1;
                } else {
                    columnValue = nextColumnValue;
                }
            }
            if (boxValue < rowValue) {
                int nextBoxValue = getValueFromOneBox(indexX, indexY, boxValue);
                if (nextBoxValue == -1) {
                    return -1;
                } else {
                    boxValue = nextBoxValue;
                }
            }
        }

        return rowValue;
    }

}
