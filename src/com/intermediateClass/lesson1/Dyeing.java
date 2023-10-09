package com.intermediateClass.lesson1;

/**
 * 预处理法
 *
 * 给一个数组，[R, G, G, R, R, G, G]
 * 左边全部染成G， 右边全部染成R， 左右边界自己定
 * 问最少染几个？
 *
 * 用一个辅助数组A，从左往右统计 0 ~ i 范围上几个 R
 * 再来一个辅助数组B，从右往左统计 i ~ n - 1 范围是几个 G
 *
 * 这样就可以拿答案了
 */
public class Dyeing {

    public static void main(String[] args) {
        String str = "RGGRRGG";
        // 1 1 1 2 3 3 3
        // 3 3 3 2 2 2 1
        System.out.println(dyeing(str.toCharArray()));
    }

    public static int dyeing(char[] arr){
        int[] arrR = new int[arr.length];
        int[] arrG = new int[arr.length];
        int countR = 0;
        int countG = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 'R'){
                countR++;
            }
            arrR[i] = countR;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] == 'G'){
                countG++;
            }
            arrG[i] = countG;
        }
        int min = arrR[0] +arrG[0];
        for (int i = 0; i < arr.length; i++) {
            if(arrR[i] +arrG[i] < min){
                min = arrR[i] + arrG[i];
            }
        }
        return min;
    }
}
