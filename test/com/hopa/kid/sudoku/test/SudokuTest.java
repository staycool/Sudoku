/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopa.kid.sudoku.test;

import com.hopa.kid.sudoku.Sudoku;

/**
 *
 * @author KID
 */
public class SudokuTest {
    /*
        sudoku raw data are listed below or given from outside.

        (1) 最难数独?
        方法1 0.101s; 方法2 8.729s
        {
        {8, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 3, 6, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 9, 0, 2, 0, 0},
        {0, 5, 0, 0, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 7, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 3, 0},
        {0, 0, 1, 0, 0, 0, 0, 6, 8},
        {0, 0, 8, 5, 0, 0, 0, 1, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        
        (2) two different answers
        {
        {0, 0, 0, 0, 7, 0, 0, 0, 0},
        {6, 4, 0, 0, 0, 2, 0, 0, 9},
        {0, 0, 8, 0, 0, 0, 0, 0, 0},
        {8, 0, 3, 5, 0, 0, 0, 0, 0},
        {0, 0, 4, 0, 8, 0, 9, 0, 0},
        {0, 0, 0, 0, 0, 9, 7, 0, 6},
        {0, 0, 0, 0, 0, 0, 3, 0, 0},
        {9, 0, 0, 1, 0, 0, 0, 6, 2},
        {5, 0, 0, 0, 4, 0, 0, 0, 0}};
        
        one answer:
        3 1 9 8 7 5 6 2 4 
        6 4 5 3 1 2 8 7 9 
        2 7 8 6 9 4 5 1 3 
        8 9 3 5 6 7 2 4 1 
        7 6 4 2 8 1 9 3 5 
        1 5 2 4 3 9 7 8 6 
        4 8 1 9 2 6 3 5 7 
        9 3 7 1 5 8 4 6 2 
        5 2 6 7 4 3 1 9 8
        another answer:
        1 2 9 8 7 5 6 4 3 
        6 4 5 3 1 2 8 7 9 
        3 7 8 6 9 4 5 2 1 
        8 9 3 5 6 7 2 1 4 
        7 6 4 2 8 1 9 3 5 
        2 5 1 4 3 9 7 8 6 
        4 1 6 9 2 8 3 5 7 
        9 8 7 1 5 3 4 6 2 
        5 3 2 7 4 6 1 9 8
    */
    public static int data[][];
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        data = new int[][]{
        {8, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 3, 6, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 9, 0, 2, 0, 0},
        {0, 5, 0, 0, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 7, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 3, 0},
        {0, 0, 1, 0, 0, 0, 0, 6, 8},
        {0, 0, 8, 5, 0, 0, 0, 1, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        
    
        
        
        Sudoku sudoku = new Sudoku(data);
        sudoku.run();
    }
}
