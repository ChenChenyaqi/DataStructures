package com.BasedAscension.fromRecursiveToDp;

/**
 * 一个数组，每个数字代表 硬币的 面值
 * 要凑成 aim 面值
 * 最少需要多少硬币
 */
public class GatherTogetherCoins {

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 3, 1};
        int aim = 6;
        System.out.println(minCoins2(arr, aim));
    }

    public static int minCoins1(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    // arr[index...] 组成出rest这么多钱，最少的硬币数返回
    public static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        // rest > 0
        if (index == arr.length) {
            return -1;
        }
        // rest > 0 且 也有硬币
        int p1 = process1(arr, index + 1, rest);
        int p2Next = process1(arr, index + 1, rest - arr[index]);
        // 如果两个可能都不能凑出硬币，返回-1
        if (p1 == -1 && p2Next == -1) {
            return -1;
        } else {
            if (p1 == -1) {
                return p2Next + 1;
            }
            if (p2Next == -1) {
                return p1;
            }
            // 两个都能凑成，选最小
            return Math.min(p1, p2Next + 1);
        }
    }

    // 严格表
    public static int minCoins2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // rest == 0时，都是 0
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }
        // index == arr.length时，都是-1
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = -1;
        }

        // 填其它位置, 从下往上, 从左往右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2Next = -1;
                if (rest - arr[index] >= 0) {
                    p2Next = dp[index + 1][rest - arr[index]];
                }
                if (p1 == -1 && p2Next == -1) {
                    dp[index][rest] = -1;
                } else {
                    if (p1 == -1) {
                        dp[index][rest] = p2Next + 1;
                    } else if (p2Next == -1) {
                        dp[index][rest] = p1;
                    } else {
                        // 两个都能凑成，选最小
                        dp[index][rest] = Math.min(p1, p2Next + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }
}
