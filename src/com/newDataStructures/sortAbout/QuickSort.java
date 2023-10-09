package com.newDataStructures.sortAbout;

import java.util.Arrays;

/**
 * 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, -1, 0, 9, 10, 3, 0, 2};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if(left < right){
            // 随机选择一个数，与 right位置的数做交换
            swap(arr, left + (int)(Math.random() * (right - left + 1)), right);

            int[] p = partition(arr, left, right);

            process(arr, left, p[0]);
            process(arr, p[1], right);
        }
    }

    private static int[] partition(int[] arr, int left, int right) {
        int target = arr[right];
        int smallIndex = left - 1;
        int bigIndex = right + 1;
        int i = left;
        while(i < bigIndex){
            if(arr[i] < target) {
                swap(arr, i, smallIndex + 1);
                smallIndex++;
                i++;
            } else if(arr[i] > target){
                swap(arr, i, bigIndex - 1);
                bigIndex--;
            } else {
                i++;
            }
        }
        return new int[]{smallIndex, bigIndex};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
