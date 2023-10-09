package com.myCompany.dynamicProgram;

/**
 * @author chenyaqi
 * @date 2021/7/29 - 19:16
 */
public class JumpingGames {
    /*
    给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

    数组中的每个元素代表你在该位置可以跳跃的最大长度。

    判断你是否能够到达最后一个下标。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        boolean res = canJump(nums);
        System.out.println("res = " + res);
    }

    private static boolean canJump(int[] nums) {
        return true;
    }
}
