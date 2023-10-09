package com.BasedAscension.fromRecursiveToDp;

/**
 * 瓜分纸牌，两个人先后从 纸牌的最左侧或最右侧 取一张牌
 * 最后取完牌了，返回胜利者的分数
 */
public class DivideTheCARDS {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        // 先手
        // 返回 拿 i 时，后手拿 i + 1 到 j， 和 拿 j 时， 后手拿 i 到 j - 1的 最大情况
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        // 后手
        // 只能选择，先手拿 i 和 先手 拿 j 时的 最小值
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }


    // dp版
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f1(arr, 0, arr.length - 1), s1(arr, 0, arr.length - 1));
    }

    // 由于 i 不能 大于 j，所以表的 左下半区 无效
    // 互相看着填
    private static int f1(int[] arr, int i, int j) {
        int[][] dp = new int[arr.length][arr.length];
        for (int k = 0; k < arr.length; k++) {
            dp[k][k] = arr[k];
        }
        return 0;
    }

    // 由于 i 不能 大于 j，所以表的 左下半区 无效
    // 互相看着填
    private static int s1(int[] arr, int i, int j) {
        int[][] dp = new int[arr.length][arr.length];
        for (int k = 0; k < arr.length; k++) {
            dp[k][k] = 0;
        }
        return 0;
    }
}
