package com.newDataStructures.hashfunction.simplefunction;

public class ModFun {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int key = remainderHash(i, 100);
            System.out.println(key);
        }
    }

    public static int remainderHash(int key, int size){
        int p = 0;
        for (int i = 2; i <= size; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if(i % j == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                p = i;
            }
        }
        return key % p;
    }
}
