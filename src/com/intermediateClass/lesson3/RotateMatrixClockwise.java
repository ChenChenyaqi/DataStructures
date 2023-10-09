package com.intermediateClass.lesson3;

import java.util.Arrays;

/**
 * 宏观调度
 * <p>
 * 给定一个正方形矩阵，只用有限几个变量，实现矩阵中每个位置的数顺时针旋转90度
 */
public class    RotateMatrixClockwise {

    public static void main(String[] args) {
        int[][] m = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(m));
        rotate(m);
        System.out.println(Arrays.deepToString(m));
    }

    public static void rotate(int[][] m) {
        // 左上角点
        int tR = 0;
        int tC = 0;
        // 右下角点
        int dR = m.length - 1;
        int dC = m[0].length - 1;
        while (tR < dR && tC < dC) {
            rotateBorder(m, tR++, tC++, dR--, dC--);
        }
    }


    // 左上角：a 行， b 列
    // 右下角：c 行， d 列
    // 旋转一个边框
    public static void rotateBorder(int[][] m, int a, int b, int c, int d) {
        // 有多少组
        for (int i = 0; i < d - b; i++) {
            int[] point1 = new int[]{a, b + i};
            int[] point2 = new int[]{a + i, d};
            int[] point3 = new int[]{c, d - i};
            int[] point4 = new int[]{c - i, b};

            swap(m, point1, point2, point3, point4);
        }
    }

    private static void swap(int[][] m, int[] point1, int[] point2, int[] point3, int[] point4) {
        int temp = m[point1[0]][point1[1]];
        m[point1[0]][point1[1]] = m[point2[0]][point2[1]];
        m[point2[0]][point2[1]] = m[point3[0]][point3[1]];
        m[point3[0]][point3[1]] = m[point4[0]][point4[1]];
        m[point4[0]][point4[1]] = temp;
    }
}
