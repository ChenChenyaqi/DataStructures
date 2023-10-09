package com.intermediateClass.lesson4;

/**
 * 给定一个数组arr，将这个数组看做一个容器
 * 请返回容器能装下多少水
 * 比如，arr = {3, 1, 2, 5, 2, 4}, 可以放 5 格子水
 *
 * 思路：只关注 i 位置的水
 * [i] = max( min(左max，右max) - arr[i],  0)
 */
public class WaterInContainer {

    public static void main(String[] args) {
        int[] arr =new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(waterInArr(arr));
    }

    public static int waterInArr(int[] arr){
        if(arr == null || arr.length < 3){
            return 0;
        }
        if(arr.length == 3){
            return Math.max(arr[1] - Math.min(arr[0], arr[2]), 0);
        }
        int maxNumL = arr[0];
        int maxNumR = arr[arr.length - 1];
        int all = 0;
        int L = 1;
        int R = arr.length - 2;
        while(L <= R){
            // 左边比右边小，左边走
            if(maxNumL < maxNumR){
                all += Math.max(0, Math.min(maxNumL, maxNumR) - arr[L]);
                if(arr[L] > maxNumL){
                    maxNumL = arr[L];
                }
                L++;
            } else {
                all += Math.max(0, Math.min(maxNumL, maxNumR) - arr[R]);
                if(arr[R] > maxNumR){
                    maxNumR = arr[R];
                }
                R--;
            }
        }
        return all;
    }
}
