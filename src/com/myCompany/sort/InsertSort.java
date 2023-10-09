package com.myCompany.sort;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/5/5 - 12:54
 */
public class InsertSort {
    public static void main(String[] args) {

        int[] arr = new int[]{5, 2, 9, 10, 1, 6, 2};
//        int[] insertSort = insertSort(arr);
//        System.out.println(Arrays.toString(insertSort));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
        test();

    }

    // 插入排序
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            int insertVal = arr[i];
            // 即arr[1]的前面这个数的下标
            int insertIndex = i - 1;

            // 给insertValue 找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 退出while循环时则找到插入的位置
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        return arr;
    }

    // 插入排序另一种写法
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
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
            insertionSort(arr1);
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
}
