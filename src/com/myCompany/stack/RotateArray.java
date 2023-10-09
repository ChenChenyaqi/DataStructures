package com.myCompany.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author chenyaqi
 * @date 2021/7/27 - 7:44
 */
public class RotateArray {
    // 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        int k = 3;

        int len = nums.length;
        k = k % len;
        Stack<Integer> stack = new Stack<>();
        for (int i = len -1 - k; i >= 0 ; i--) {
            stack.add(nums[i]);
        }
        for (int i = len - 1; i >= len - k; i--) {
            stack.add(nums[i]);
        }
        for (int i = 0; i < len; i++) {
            nums[i] = stack.pop();
        }
        System.out.println(Arrays.toString(nums));
    }
}
