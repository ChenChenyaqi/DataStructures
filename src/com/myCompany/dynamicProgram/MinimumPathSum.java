package com.myCompany.dynamicProgram;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/7/24 - 14:42
 */
public class MinimumPathSum {
    // 最小路径和
    /*
    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步
    1,3,1
    1,5,1
    4,2,1
     */
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        };
        int res = getMiniPath(grid);
        System.out.println("res = " + res);
        System.out.println(Arrays.deepToString(grid));
    }

    /**
     * 分析：path为一个与grid同样大小的二维数组，其每个点的值代表从左上角到达此处的总和
     * 由于每次只能向右或向下走，所以每个点的值，就是左边或上边其中最小的一个，再加上此点的值
     * 由于走过一点后，便不会再次走，因此path会一一覆盖grid，可以直接使用grid
     * @param grid
     * @return
     */
    private static int getMiniPath(int[][] grid) {
        int pre = grid[0][0];
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (i == 0 && j != 0){
                    pre = grid[i][j - 1] + grid[i][j];
                }
                if (i != 0 && j == 0){
                    pre = grid[i -1][j] + grid[i][j];
                 }
                if (i != 0 && j != 0){
                    pre = Math.min(grid[i][j -1] , grid[i -1][j]) + grid[i][j];
                }
                grid[i][j] = pre;
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
