package com.BasedAscension.fromRecursiveToDp;

/**
 * 给你一个棋盘，(x, y)是 马 要去的地方， step 是你只能走完这么多步到那里
 * 马 从(0, 0)位置开始跳
 * 问 有多少种方法可以走到(x, y)
 */
public class ChessProblem {

    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        int step = 4;
        System.out.println(process(x, y, step));
        System.out.println(dpWays(x, y, step));
    }

    // 去往(x, y)位置，必须跳step步
    public static int process(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        if (step == 0) {
            return (x == 0 && y == 0) ? 1 : 0;
        }
        // 要到达的位置不越界，也有步数可以跳
        return process(x - 1, y + 2, step - 1)
                + process(x + 1, y + 2, step - 1)
                + process(x + 2, y + 1, step - 1)
                + process(x + 2, y - 1, step - 1)
                + process(x + 1, y - 2, step - 1)
                + process(x - 1, y - 2, step - 1)
                + process(x - 2, y - 1, step - 1)
                + process(x - 2, y + 1, step - 1);
    }

    // dp 版
    public static int dpWays(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || step < 0) {
            return 0;
        }
        int[][][] dp = new int[step + 1][9][10];
        dp[0][0][0] = 1;
        for (int h = 1; h <= step; h++) { // 层
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 10; c++) {
                    dp[h][r][c] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[h][r][c] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[h][r][c] += getValue(dp, r + 1, c + 2, h - 1);
                    dp[h][r][c] += getValue(dp, r + 2, c + 1, h - 1);
                    dp[h][r][c] += getValue(dp, r + 2, c - 1, h - 1);
                    dp[h][r][c] += getValue(dp, r + 1, c - 2, h - 1);
                    dp[h][r][c] += getValue(dp, r - 1, c - 2, h - 1);
                    dp[h][r][c] += getValue(dp, r - 2, c - 1, h - 1);
                    dp[h][r][c] += getValue(dp, r - 2, c + 1, h - 1);
                }
            }
        }
        return dp[step][x][y];
    }

    private static int getValue(int[][][] dp, int row, int col, int step) {
        if (row < 0 || row > 8 || col < 0 || col > 9) {
            return 0;
        }
        return dp[step][row][col];
    }
}
