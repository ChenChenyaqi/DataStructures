package com.BasedAscension.fromRecursiveToDp;


/**
 * N: 代表从 1 到 N 个点
 * S: 代表机器人当前在哪个点上
 * e: 代表机器人目的地是哪个点
 * k: 代表机器人必须走多少步才能停下
 * 问，从 s -> e 共有多少种不同的路线
 */
public class RobotFindPath {

    // 暴力递归版
    public static int walkWay1(int N, int E, int S, int K) {
        return f1(N, E, K, S);
    }

    // rest: 剩余多少步
    // cur: 当前位置
    public static int f1(int N, int E, int rest, int cur) {
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }
        if (cur == 1) {
            return f1(N, E, rest - 1, 2);
        }
        if (cur == N) {
            return f1(N, E, rest - 1, N - 1);
        }
        // 中间位置
        return f1(N, E, rest - 1, cur - 1) + f1(N, E, rest - 1, cur + 1);
    }


    // 记忆化搜索, 考虑所有可变参数的组合
    public static int walkWay2(int N, int E, int S, int K) {
        int[][] dp = new int[K + 1][N + 1];
        // 默认值改为 -1
        for (int i = 0; i < K + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(N, E, K, S, dp);
    }

    public static int f2(int N, int E, int rest, int cur, int[][] dp) {
        if (dp[rest][cur] != -1) {
            // 如果这个状态算过，就返回算过的值
            return dp[rest][cur];
        }
        // 缓存没命中
        if (rest == 0) {
            dp[rest][cur] = cur == E ? 1 : 0;
        } else if (cur == 1) {
            dp[rest][cur] = f2(N, E, rest - 1, 2, dp);
        } else if (cur == N) {
            dp[rest][cur] = f2(N, E, rest - 1, N - 1, dp);
        } else {
            dp[rest][cur] = f2(N, E, rest - 1, cur - 1, dp) + f2(N, E, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }


    // 严格表结构: 不要直接找动态转移方程！！！！！ 直接试
    public static int walkWay3(int N, int E, int S, int K) {
        // 根据 f1 递归版本
        int[][] dp = new int[K + 1][N + 1];
        // 初始化dp的第一行，对应于 f1 中第一个条件
        dp[0][E] = 1;

        // 根据f1，最左和最右的两列依赖于 左上角的和右上角的
        // 其余依赖 左上角 + 右上角
        for (int i = 1; i < K + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][S];
    }

}
