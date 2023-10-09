package com.BasedAscension.recursive;

/**
 * 给一个二维矩阵
 * 矩阵的每个位置要么 0 要么 1
 * 每个 1 （可以上下左右连成一片）的区域是一个岛
 * 求这个二维矩阵中有多少个岛
 */
public class IslandProblem {

    public static int getIslandCount(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res++;
                infect(matrix, i, j, row, col);
            }
        }
        return res;
    }

    // 把 1 的区域 变为 2
    private static void infect(int[][] matrix, int i, int j, int row, int col) {
        if(i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] != 1){
            return;
        }
        matrix[i][j] = 2;
        infect(matrix, i - 1, j, row, col);
        infect(matrix, i + 1, j, row, col);
        infect(matrix, i, j - 1, row, col);
        infect(matrix, i, j + 1, row, col);
    }
}
