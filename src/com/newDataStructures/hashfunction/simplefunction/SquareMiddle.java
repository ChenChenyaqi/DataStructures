package com.newDataStructures.hashfunction.simplefunction;

import java.util.Arrays;

public class SquareMiddle {
    public static void main(String[] args) {
        int[] arr = new int[101];
        for (int i = 0; i <= 10000; i++) {
            int key = squareHash(i);
            arr[key]++;
        }
        System.out.println(Arrays.toString(arr));

    }

    public static int squareHash(int key){
        if(key > 46340){
            return 0;
        }
        int squareKey = key * key;
        String str = String.valueOf(squareKey);
        int len = str.length();
        String res = "";
        if(len % 2 == 0){
            int right = len / 2;
            int left = right - 1;
            res = str.substring(left, right + 1);
        } else {
            if(len == 1){
                res = str;
            } else {
                int target = len / 2;
                res = str.substring(target, target + 1);
            }
        }
        return Integer.parseInt(res);
    }
}
