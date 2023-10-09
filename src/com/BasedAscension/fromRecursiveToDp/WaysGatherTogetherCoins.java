package com.BasedAscension.fromRecursiveToDp;

/**
 * [3, 5, 10, 2] 四种面值的货币，任意张，无重复
 * 组合出 aim = 1000
 * 组合方式有多少种？
 */
public class WaysGatherTogetherCoins {

    public static void main(String[] args) {
        int[] arr = {25, 10, 5, 1};
        int aim = 929782;
        // System.out.println(way1(arr, aim));
        // System.out.println(way2(arr, aim));
        System.out.println(way3(arr, aim)) ;
        System.out.println(way4(arr, aim));
    }

    public static int way1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - arr[index] * zhang);
        }
        return ways;
    }

    // dp 版
    public static int way2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - arr[index] * zhang];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    // dp 版改进
    // 斜率优化：如果有枚举行为，则观察看看 有没有临近的数据可以替代枚举行为，只和观察有关，与题意无关
    public static int way3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] ;
                if(rest - arr[index] >= 0){
                    dp[index][rest] += dp[index][rest - arr[index]]  % 1000000007;
                }
            }
        }
        return dp[0][aim] % 1000000007;
    }

    public static int way4(int[] arr, int aim){
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int c = 0; c < 4; ++c) {
            int coin = arr[c];
            for (int i = coin; i <= aim; ++i) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }
        return dp[aim];
    }
}
