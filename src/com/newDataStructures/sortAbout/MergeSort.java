package com.newDataStructures.sortAbout;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, -1, 0, 9, 10, 3, 0, 2};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 归并排序
    private static void mergeSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if(left == right){
            return;
        }
        int mid = left + (right - left) / 2;
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left , mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while(p1 <= mid && p2 <= right){
            tempArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            tempArr[i++] = arr[p1++];
        }
        while(p2 <= right){
            tempArr[i++] = arr[p2++];
        }
        for (int j = 0; j < tempArr.length; j++) {
            arr[left + j] = tempArr[j];
        }
    }
}
