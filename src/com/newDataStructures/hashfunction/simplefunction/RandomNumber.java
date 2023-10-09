package com.newDataStructures.hashfunction.simplefunction;

import java.util.Random;

public class RandomNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomHash(i));
        }
    }

    public static int randomHash(int key){
        Random random = new Random(key);
        int a = (int)(random.nextDouble() * 10000);
        return a % 100;
    }
}
