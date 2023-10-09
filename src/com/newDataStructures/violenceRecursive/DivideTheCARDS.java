package com.newDataStructures.violenceRecursive;

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


}
