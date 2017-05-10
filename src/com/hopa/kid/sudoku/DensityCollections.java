/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopa.kid.sudoku;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author KID
 */
public class DensityCollections {
    
    
        
    private final int ROW_COUNT = 9;
    private final int COLUMN_COUNT = 9;
    private final int BOX_COUNT = 9;
    
    //生成数组a所对应的密度数组

    /**
     *
     * @param raw
     * @return 返回二维密度数组
     */
    public int[][] toTwoDimensionalDensityArray(int raw[][]) {
        if (raw == null) {
            return null;
        }
        
        //横向,纵向,九宫格的密度值范围:0~8
        //行密度
        int rowNum[] = new int[ROW_COUNT];
        //列密度
        int columnNum[] = new int[COLUMN_COUNT];
        //九宫格密度
        int boxNum[] = new int[BOX_COUNT];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (raw[i][j] != 0) {
                    rowNum[i]++;
                }

                if (raw[j][i] != 0) {
                    columnNum[i]++;
                }

                if (raw[i][j] != 0) {
                    /*
                        0   1   2
                        3   4   5
                        6   7   8
                     */
                    boxNum[(i / 3) * 3 + j / 3]++;
                }
            }
        }

        //密度数组d,总密度值范围:0~24
        int d[][] = new int[9][9];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (raw[i][j] != 0) {
                    d[i][j] = -1;
                } else {
                    d[i][j] = rowNum[i] + columnNum[j] + boxNum[(i / 3) * 3 + j / 3];
                }
            }
        }
        
        return d;
    }
    
    //密度数组(二维数组)转换成按降序排列的一维数组,不包括(-1)值

    /**
     *
     * @param d 二维密度数组
     * @return 返回一维降序密度数组
     */
    public Density[] toDescOneDimensionalArray(int d[][]) {
        //Java7之前API,
        //ArrayList<Density> densityList = new ArrayList<Density>();

        ArrayList<Density> densityList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (d[i][j] != -1) {
                    densityList.add(new Density(i, j, d[i][j]));
                }
            }
        }
        Collections.sort(densityList);
        //System.out.printf("Original  sort, list:%s\n", densityList);

        Density[] density = new Density[densityList.size()];
        densityList.toArray(density);
        return density;
    }
}
