package com.myCompany.dynamicProgram;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/7/26 - 11:32
 */
public class LongestIncreasingSubsequence {
    /*
    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

    子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
    例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

     */
    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int res = lengthOfLIS(nums);
        System.out.println("res = " + res);
    }

    // 使用动态规划
    private static int lengthOfLIS(int[] nums) {
        if (nums.length == 0){
            return 0;
        }

        // 定义dp[i]为选择第i个数时的  前i个数里最长递增子序列的长度
        int[] dp = new int[nums.length];
        // 初始化，只选择一个数，长度为1
        Arrays.fill(dp,1);
        // 初始化maxLen为1
        int maxLen = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 如果选择的i比i前面的一个数大，则i就可以加入进去
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
