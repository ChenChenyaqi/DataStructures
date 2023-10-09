package com.myCompany.dynamicProgram;

/**
 * @author chenyaqi
 * @date 2021/6/3 - 15:47
 */
public class MaxSumOfContiguousSubarrays {
    // 连续子序列最大和
    public static void main(String[] args) {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = {1, 2, 3, -1, -2};
        int maxSum = getMaxSum(nums);
        System.out.println("maxSum = " + maxSum);
    }

    private static int getMaxSum(int[] nums) {
        int preResult;
        preResult = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preResult = Math.max(nums[i], preResult + nums[i]);
            max = Math.max(max,preResult);
        }
        return max;
    }
}
