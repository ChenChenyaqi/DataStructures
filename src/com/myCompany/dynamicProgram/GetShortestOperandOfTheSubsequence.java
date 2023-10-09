package com.myCompany.dynamicProgram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyaqi
 * @date 2021/7/26 - 9:47
 */
public class GetShortestOperandOfTheSubsequence {
    /*
    给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。

    每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，
    那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。

    请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。

    一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），
    同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
     */
    public static void main(String[] args) {
        int[] target = new int[]{6, 4, 8, 1, 3, 2};
        int[] arr = new int[]{4, 7, 6, 2, 3, 8, 6, 1};

        int res = minOperations(target, arr);
        System.out.println("res = " + res);
    }

    // 解题要点：对arr数组中元素在target中的下标所组成的index数组进行寻找最长递增的子序列
    public static int minOperations(int[] target, int[] arr) {
        int targetLength = target.length;
        int arrLength = arr.length;
        // 创建index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < targetLength; i++) {
            map.put(target[i], i);
        }
        int[] allIndex = new int[arrLength];
        int i = 0;
        int len = 0;
        for (int t : arr) {
            if (map.containsKey(t)) {
                allIndex[i] = map.get(t);
                len ++;
            } else {
                allIndex[i] = targetLength;
            }
            i++;
        }

        // 排除arr里没有target的情况
        if (len == 0){
            return targetLength;
        }
        int[] index = new int[len];
        i = 0;
        for (int value : allIndex) {
            if (value < targetLength) {
                index[i] = value;
                i++;
            }
        }
        // dp[i] 为选择第i个数的情况下  的前i个数的最长递增子序列长度
        int[] dp = new int[len];
        int max = 1;
        Arrays.fill(dp,1);
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < j; k++) {
                if (index[j] > index[k]){
                   dp[j] = Math.max(dp[j], dp[k] + 1);
                }
            }
            max = Math.max(dp[j], max);
        }
        return targetLength - max;
    }
}
