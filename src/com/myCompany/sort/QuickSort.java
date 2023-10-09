package com.myCompany.sort;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/8/6 - 13:36
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, 8, 1, 2, 3, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));

        test();

        // 测试时间
        int[] nums1 = new int[1000000];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = (int)(Math.random() * 1000);
        }
        int[] nums2 = Arrays.copyOf(nums1,nums1.length);
        
        // 快排
        long before1 = System.currentTimeMillis();
        quickSort(nums1,0,nums1.length - 1);
        long after1 = System.currentTimeMillis();
        long time1 = (after1 - before1);

        // 系统自带的
        long before2 = System.currentTimeMillis();
        Arrays.sort(nums2);
        long after2 = System.currentTimeMillis();
        long time2 = (after2 - before2);

        // 时间差对比
        System.out.println("time1 = " + time1);
        System.out.println("time2 = " + time2);

        // 测试荷兰国旗问题：
        int[] nums = {2, 0, 5, 5, 16, 8, 3, 4, 9, 10};
        DutchFlagQuestion(nums, 5);
        System.out.println("荷兰国旗问题：" + Arrays.toString(nums));
    }


    /**
     * 快排之前
     * 荷兰国旗问题：把一个数组中的所有数分为三个区域，前面都是小于target的，中间是等于target的，后面是大于target的
     * 以荷兰国旗问题为原型弄出来的快排(2.0版本)，是O(n^2)算法，例子arr = {1,2,3,4,5,6,7,8};
     * 每次只有左区域，倒着依次处理
     *
     * @param arr    arr数组
     * @param target 分割依据数
     */
    public static void DutchFlagQuestion(int[] arr, int target) {
        // 小于target的区域
        int smallIndex = -1;
        // 大于target的区域
        int bigIndex = arr.length;
        int curIndex = 0;
        while (curIndex < bigIndex) {
            // smallIndex向右扩张
            if (arr[curIndex] < target) {
                swap(arr, curIndex, smallIndex + 1);
                curIndex++;
                smallIndex++;
            } else if (arr[curIndex] > target) { // bigIndex向左扩张
                swap(arr, curIndex, bigIndex - 1);
                bigIndex--;
            } else {
                curIndex++;
            }
        }
    }

    /**
     * 基于荷兰问题的快速排序
     *
     * @param arr   待排数组
     * @param left  待排区域左端点
     * @param right 待排区域右端点
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            swap(arr, left + (int)(Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            // < 区域
            quickSort(arr, left, p[0]);
            // > 区域
            quickSort(arr, p[1], right);
        }
    }

    private static int[] partition(int[] arr, int left, int right) {
        int target = arr[right];
        // 小于target的区域
        int smallIndex = left - 1;
        // 大于target的区域
        int bigIndex = right + 1;
        int curIndex = left;
        while (curIndex < bigIndex) {
            // smallIndex向右扩张
            if (arr[curIndex] < target) {
                swap(arr, curIndex, smallIndex + 1);
                curIndex++;
                smallIndex++;
            } else if (arr[curIndex] > target) { // bigIndex向左扩张
                swap(arr, curIndex, bigIndex - 1);
                bigIndex--;
            } else {
                curIndex++;
            }
        }
        return new int[]{smallIndex, bigIndex};
    }


    // 对数器方法
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    // 测试对比
    public static void test() {
        // 测试次数
        int testTimes = 500000;
        // 数组最大长度
        int maxSize = 100;
        // 数组元素最大值
        int maxValue = 100;
        // 测试是否通过
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            // 产生两个测试数据
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1,arr1.length);
            // 两个方法分别进行
            quickSort(arr1, 0, arr1.length - 1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    // 判断是否相等
    private static boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    // 产生随机数据
    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] nums = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random() * (maxValue+1));
        }
        return nums;
    }

    
    /**
     * 交换元素通用处理
     *
     * @param arr 待交换数组
     * @param a   交换元素下标
     * @param b   交换元素下标
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
