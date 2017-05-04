/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author KID
 */
public class Test1 {
    public static void main(String[] args) {
        int a = 5;
        int d = 2;
//        for (int j = 1; j < 10; j++) {
//            for (int i = 0; i < 10; i++) {
//                if (i > a) {
//                    System.out.println("i: " + i);
//                    break;
//                }
//            }
//            System.out.println("j: " + j);
//        }
//        
//        
//        System.out.println(a);
//        System.out.println(d);
//        
//        System.out.println(1/3);
        a(a, d);

        
    }
 
    public static void a(int a, int b) {
        a = a + 1;
        b = b + 2;
        System.out.println(a);
        System.out.println(b);
        
        int c[][] = {{2},{2,3}};
    }
}
