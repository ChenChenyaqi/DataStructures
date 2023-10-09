package com.myCompany.sort;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/8/5 - 15:15
 */
public class MergeSort {
    // 小和
    private static int minSum = 0;
    // 逆序对数
    private static int reverseSum = 0;


    // 归并排序
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};  // 12 + 10 + 3 + 3 = 28 ; 1+1+1+4+4+2+6 = 19
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(minSum);
        System.out.println(reverseSum);
    }

    public static void sort(int[] arr) {
        // 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // 左边归并排序，使得左子序列有序
            sort(arr, left, mid, temp);
            // 右边归并排序，使得右子序列有序
            sort(arr, mid + 1, right, temp);
            // 将两个有序子数组合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    // 归并排序，顺便求小和数、逆序对数：
    //          在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
    //          例如：[1,3,4,2,5]，1:   左边比1小到数没有；
    //                            3：  左边 1；
    //                            4:    1+3；
    //                            2:    1；
    //                            5:    1+3+4+2  sum = 1+1+3+1+1+3+4+2 = 16
    //          等价于：1:  右边比1大的有4个数，4*1；
    //                 3:   2个数，2*3；
    //                 4:   1*4；
    //                 2:   1*2
//                     sum = 4*1 + 2*3 + 1*4 + 1*2 = 16
//              在一个数组中，若左边的数比右边大，则这俩数构成一个逆序对  <==> 一个数的左边有多少个数比它大
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 左序列指针
        int i = left;
        // 右序列指针
        int j = mid + 1;
        // 临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            // 求小和，右边从 j 开始到 right 都比 i 处的大
            minSum += arr[i] < arr[j] ? (right - j + 1) * arr[i] : 0;
            // 逆序对数
            reverseSum += arr[i] > arr[j] ? (mid - i + 1) : 0;
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        // 将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        // 将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
