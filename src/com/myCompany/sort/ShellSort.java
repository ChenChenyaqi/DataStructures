package com.myCompany.sort;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/5/5 - 15:00
 */
public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date1);
        System.out.println("排序前：" + dateStr);
        shellSort2(arr);
//        Arrays.sort(arr);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后：" + dateStr2);*/

        int[] arr = new int[]{2, 10, 3, 95, 65, 25};
        shellSort2(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    // 希尔排序
    public static int[] shellSort(int[] arr) {
        // 增量gap并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        return arr;
    }

    // 改良希尔排序
    public static int[] shellSort2(int[] arr) {
        // 增量gap并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }

            }
        }
        return arr;
    }
}
