package com.myCompany.search;

/**
 * 插值查找（对于数据量大，分布均匀的情况下）
 * @author chenyaqi
 * @date 2021/6/7 - 10:07
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }
        int index = insertSearch(nums, 1);
        System.out.println("index = " + index);
    }

    private static int insertSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left <= right){
            mid = left + (right - left) * (target - nums[left])/(nums[right] - nums[left]);
            if (nums[mid] == target){
                return mid;
            } else if (target > nums[mid]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid;
    }

}
