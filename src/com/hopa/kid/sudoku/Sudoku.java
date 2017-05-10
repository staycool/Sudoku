/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopa.kid.sudoku;

/**
 *
 * @author KID
 */
public class Sudoku {

    private final int ROW_COUNT = 9;
    private final int COLUMN_COUNT = 9;
    private final int BOX_COUNT = 9;

    //原始数据
    int rawUnit[][];
    //进行完善(填数字)的数独数据
    int unit[][];
    //降序一维密度数组
    private Density density[];

    public Sudoku() {
        this.rawUnit = null;
    }

    public Sudoku(int data[][]) {

        //需验证数组data的有效性,如有9*9方格等;
        this.rawUnit = data;
    }

    public void run() {

        if (rawUnit == null) {
            return;
        } else {
            unit = rawUnit;
        }
        printSudoku();
        selectAlgorithm(1);
        printSudoku();
    }

    /**
     * 
     * @param index 方法对应的序号
     */
    private void selectAlgorithm(int index) {
        boolean isSucc = false;
        long startTime = System.currentTimeMillis();
        switch (index) {
            case 1:
                isSucc = calc(0, -1);
                break;
            case 2: {
                DensityCollections dc = new DensityCollections();
                int dd[][] = dc.toTwoDimensionalDensityArray(rawUnit);
                density = dc.toDescOneDimensionalArray(dd);
                printArray(dd, ROW_COUNT, COLUMN_COUNT);
                isSucc = calcByd(0);
                break;
            }
            default:
                break;
        }
        long endTime = System.currentTimeMillis();
        //System.out.println("Sudoku is successfully solved? " + isSucc);
        System.out.println("Total time: " + (float) (endTime - startTime) / 1000.0 + "s");
    }

    /**
     * 方法1 递归算法,还是回溯算法
     *
     * @param indexX
     * @param indexY
     * @return
     */
    private boolean calc(int indexX, int indexY) {
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

    /**
     * 方法2
     *
     * @param kk
     * @return
     */
    private boolean calcByd(int kk) {
        //if (density == null) {
        //    return false;
        //}

        //kk++;
        //System.out.println("kk: " + kk);
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
                unit[i][j] = 0;
                return false;
            }
        }

        //全部遍历完成
        return true;
    }

    /**
     * set one value from one row(行)
     *
     * @param indexX
     * @param indexY
     * @return
     */
    private int getValueFromOneRow(int indexX, int indexY) {
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
    private int getValueFromOneRow(int indexX, int indexY, int startValue) {
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
    private int getValueFromOneColumn(int indexX, int indexY) {
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
    private int getValueFromOneColumn(int indexX, int indexY, int startValue) {
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
    private int getValueFromOneBox(int indexX, int indexY) {
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
    private int getValueFromOneBox(int indexX, int indexY, int startValue) {
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
    private boolean isValidatedValue(int rowValue, int columnValue, int boxValue) {
        return rowValue == columnValue
                && columnValue == boxValue
                && 0 < rowValue
                && rowValue <= 9;
    }

    private int getCorrectValue(int indexX, int indexY) {
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

    //打印数独
    private void printSudoku() {
        printArray(unit, ROW_COUNT, COLUMN_COUNT);
    }

    //Basic print function,used to print row * col array.
    private void printArray(int array[][], int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%3d", array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
