package com.myCompany.sort;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/5/5 - 10:29
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int)(Math.random() * 80000);
//        }
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = simpleDateFormat.format(date1);
//        System.out.println("排序前："+ dateStr);
//        bubbleSort(arr);
//        Date date2 = new Date();
//        String dateStr2 = simpleDateFormat.format(date2);
//        System.out.println("排序后："+ dateStr2);
        int[] arr = new int[]{5, 2, 9, 10, 1, 6, 2};
        int[] bubbleSort = bubbleSort(arr);
        System.out.println("bubbleSort = " + Arrays.toString(bubbleSort));

    }

    public static int[] bubbleSort(int[] arr) {
        // 冒泡排序
        // 临时变量temp
        int temp = 0;
        // 表示是否进行过交换
        boolean flag = false;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        return arr;
    }
}
