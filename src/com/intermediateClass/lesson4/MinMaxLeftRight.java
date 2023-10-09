package com.intermediateClass.lesson4;

/**
 * 给你一个长度为N的数组，怎么划分一刀
 * 使得（左边的最大值 - 右边的最大值）的绝对值最大
 * 返回这个值
 *
 * 方法一：预处理，准备两个数组，分别表示，由左往右（由右往左）的最大值
 * 方法二：先找到全局最大值，如果max 在左边，则就让右部分只有 N - 1这个数
 *                          如果max 在右边，则就让左部分只有 0 这个数
 */
public class MinMaxLeftRight {

    public static int process(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
        }
        return Math.max(max - arr[0], max - arr[1]);
    }
}
