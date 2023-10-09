package com.newDataStructures.hashfunction.simplefunction;

public class DirectAddress {
    public static void main(String[] args) {

    }

    public static int directHash(int key){
        return key;
    }

    public static int directHash(int key, int a, int b){
        return a*key + b;
    }
}
