package com.intermediateClass.lesson4.dpSizeSmall;

/**
 * 动态规划的空间压缩技巧（一个点与自己上边，左边是数据有关，则可以压缩为一行，一个数组）
 * 如果 i,j 位置的数据需要 (i-1, j) ,(i-2,j-1), (i, j-2) 的数据
 * 则可以准备三个数组，轮流更新
 * 如果一个点跳的太远，则没必要压缩了
 * <p>
 * 例：给你一个二维数组matrix，其中每个数都是正数，求从左上角走到右下角
 * 每一步只能向右或者向下，沿途经过的数字要累加起来。返回最小的路径和
 */
public class MinPathInMatrix {

    // i,j 当前来到的位置
    public static int process(int[][] m, int i, int j) {
        if (i == m.length || j == m[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == m.length - 1 && j == m[0].length - 1) {
            return m[i][j];
        }
        int sum = m[i][j];
        int right = process(m, i, j + 1);
        int down = process(m, i + 1, j);
        sum += Math.min(right, down);
        return sum;
    }

    public static int dpProcess(int[][] m) {
        if(m == null || m.length == 0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row +1 ][col+ 1];
        // 处理越出边界的情况
        for (int i = 0; i <= row; i++) {
            dp[i][col] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= col; i++) {
            dp[row][i] = Integer.MAX_VALUE;
        }

        // 其它情况
        dp[row - 1][col - 1] = m[row - 1][col - 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if(i == row - 1 && j == col - 1){
                    continue;
                }
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + m[i][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(dpProcess2(m));
    }

    public static int dpProcess2(int[][] m){
        if(m == null || m.length == 0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        // 处理最后一行
        dp[col - 1] = m[row - 1][col - 1];
        for (int i = col - 1; i >= 0; i--) {
            if(i == col - 1){
                continue;
            }
            dp[i] = m[row - 1][i] + dp[i + 1];
        }
        for (int i = row - 1; i >= 0; i--) {
            if(i == row - 1){
                continue;
            }
            for (int j = col - 1; j >= 0 ; j--) {
                if(j == col - 1){
                    dp[j] = m[i][j] + dp[j];
                    continue;
                }
                dp[j] = m[i][j] +Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
