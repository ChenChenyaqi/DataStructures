package com.myCompany.search;

/**
 * @author chenyaqi
 * @date 2021/6/7 - 8:51
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(nums, 1000);
        System.out.println("index = " + index);
        int[] arr = {5, 2, 4, 6, 1, 3, 10};
        System.out.println(binarySearchLocalMinNumber(arr));
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid;
    }

    /**
     * 查找一个数组(互不相同)中一个 局部最小 的数
     * 局部最小定义：下标为0时，若 nums[0] < nums[1] 则 nums[0]就是局部最小数
     * 下标为len - 1 = nums.length-1时，若 nums[len -1] < nums[len - 2] 则nums[len - 1]是局部最小数
     * 下标为 i 时，若 nums[i - 1] > nums[i] < nums[i + 1]， 则nums[i]是局部最小数
     * * @param nums
     *
     * @return
     */
    public static int binarySearchLocalMinNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -2;
        }
        if (nums.length == 1) {
            return -1;
        }
        if (nums[0] < nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] < nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid - 1] < nums[mid]) {
                right = mid - 1;
            } else if (nums[mid + 1] < nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;
    }
}
