package com.myCompany.tenAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/5/29 - 8:11
 */
public class BinarySearch {
    // 二分查找（非递归）
    public static void main(String[] args) {
        int[] arr = {2, 10, 15, 80, 111, 1000,1000,1000};
        System.out.println(binarySearchNoRecursion(arr, 1000));
        System.out.println(binarySearchRecursion(arr, 0, arr.length - 1, 1000));
        System.out.println(binarySearchNoRecursionPro(arr,1000));
        System.out.println(binarySearchRecursionPro(arr,0,arr.length-1,1000));
    }

    /**
     * 非递归的二分查找
     * 根据目标值在数组中查找所在下标
     *
     * @param arr    数组，升序排列
     * @param target 目标
     * @return 返回目标的下标，-1代表没找的
     */
    public static int binarySearchNoRecursion(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        // 说明可以进行查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid]) {
                // 目标在右边，left右移
                left = mid + 1;
            } else if (target < arr[mid]) {
                // 目标在左边，right左移
                right = mid - 1;
            } else if (target == arr[mid]) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归的二分查找
     * 根据目标值在数组中查找所在下标
     *
     * @param arr    数组
     * @param left   左指针
     * @param right  右指针
     * @param target 目标值
     * @return 返回目标的索引，没有返回-1
     */
    public static int binarySearchRecursion(int[] arr, int left, int right, int target) {
        // 递归结束条件
        if (left > right) {
            return -1;
        }
        // 中间坐标
        int mid = (left + right) / 2;

        if (target > arr[mid]) {
            // 向右递归
            return binarySearchRecursion(arr, mid + 1, right, target);
        } else if (target < arr[mid]) {
            // 向左递归
            return binarySearchRecursion(arr, left, mid - 1, target);
        } else {
            return mid;
        }

    }

    /**
     * 非递归的二分查找（查找多个）
     * @param arr 数组
     * @param target 目标值
     * @return 返回目标索引的集合
     */
    public static List<Integer> binarySearchNoRecursionPro(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        // 说明可以进行查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid]) {
                // 目标在右边，left右移
                left = mid + 1;
            } else if (target < arr[mid]) {
                // 目标在左边，right左移
                right = mid - 1;
            } else if (target == arr[mid]) {
                // 找到目标值
                // 向左遍历
                int temp = mid - 1;
                List<Integer> resIndexList = new ArrayList<>();
                // 左遍历
                while (temp >= 0 && arr[temp] == target) {
                    resIndexList.add(temp);
                    temp--;
                }
                resIndexList.add(mid);
                // 向右遍历
                temp = mid + 1;
                while (temp <= arr.length - 1 && arr[temp] == target) {
                    resIndexList.add(temp);
                    temp++;
                }
                return resIndexList;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 递归的二分查找（查找多个）
     * 根据目标值在数组中查找所在下标
     *
     * @param arr    数组
     * @param left   左指针
     * @param right  右指针
     * @param target 目标值
     * @return 返回目标索引集合
     */
    public static List<Integer> binarySearchRecursionPro(int[] arr, int left, int right, int target) {
        // 递归结束条件
        if (left > right) {
            return new ArrayList<>();
        }
        // 中间坐标
        int mid = (left + right) / 2;

        if (target > arr[mid]) {
            // 向右递归
            return binarySearchRecursionPro(arr, mid + 1, right, target);
        } else if (target < arr[mid]) {
            // 向左递归
            return binarySearchRecursionPro(arr, left, mid - 1, target);
        } else {
            // 找到目标值
            // 向左遍历
            int temp = mid - 1;
            List<Integer> resIndexList = new ArrayList<>();
            // 左遍历
            while (temp >= 0 && arr[temp] == target) {
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            // 向右遍历
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == target) {
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
