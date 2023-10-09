package com.operationsResearch.connectionNumbers;

/**
 * 输入：各个点的坐标，横向向右为 x 轴，纵向向下为 y 轴, (x, y)
 *      比如：1,1,2,2  1：(50, 0) 1：(50, 100) 2:(0, 50) 2:(80, 50)
 *      坐标只是大致估计，并不需要严格精确，能正确表示各个点的相对位置即可
 */
public class Main {
    public static void main(String[] args) {
        int res = minGold(10);
        System.out.println(res);
    }

    public static int minGold(int n){
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = i;
        }

        for (int L = n - 2; L >= 1; L--) {
            for (int R = L + 2; R <= n; R++) {
                int min = Math.min(L + dp[L + 1][R], R + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    min = Math.min(min, i + Math.max(dp[L][i - 1], dp[i + 1][R]));
                }
                dp[L][R] = min;
            }
        }
        return dp[1][n];
    }
}
