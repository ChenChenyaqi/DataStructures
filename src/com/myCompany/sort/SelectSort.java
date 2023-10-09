package com.myCompany.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenyaqi
 * @date 2021/5/5 - 11:08
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date1);
        System.out.println("排序前："+ dateStr);
        selectSort(arr);
        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+ dateStr2);
    }

    // 选择排序
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 假定第i个数是最小值
            int minIndex = i;
            int min = arr[i];
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            // 如果第i个和minIndex不一样则交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
}
