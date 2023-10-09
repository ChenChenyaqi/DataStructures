package com.intermediateClass.lesson3;

import java.util.Arrays;

/**
 * 斜着从上往下，按斜线打印矩阵（任意型号）
 * <p>
 * 思路：写一个可以根据两点，打印一个斜线的函数
 * a，b两点最开始都在矩阵左上角，a 往右走，走到不能再往右了，就往下走
 * b 往下走，走到不能再往下了，就往右走
 * 每次同时移动一次，就打印这条斜线
 * <p>
 * 1 2 3 4
 * 5 6 7 8
 * 9 1 2 3
 */
public class ZigZagPrint {

    public static void main(String[] args) {
        int[][] m = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(m));
        printMatrixZigZag(m);
    }

    public static void printMatrixZigZag(int[][] matrix) {
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (ar != endR + 1) {
            printLevel(matrix, ar, ac, br, bc, fromUp);
            ar = ac == endC ? ar + 1 : ar;
            ac = ac == endC ? ac : ac + 1;
            bc = br == endR ? bc + 1 : bc;
            br = br == endR ? br : br + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    // int tR, int tC, int dR, int dC 分别为两个点的行列号
    // f 控制是从下往上打印，还是从上往下打印
    private static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            // 从右上往左下打印
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            // 从左下往右上打印
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }
}
