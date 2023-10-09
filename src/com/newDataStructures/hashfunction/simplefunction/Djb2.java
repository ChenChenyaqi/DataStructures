package com.newDataStructures.hashfunction.simplefunction;

public class Djb2 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(djb2Hash(i));
        }
    }

    public static int djb2Hash(Object key){
        String keyStr = String.valueOf(key);
        int hash = 5381;
        for (int i = 0; i < keyStr.length(); i++) {
            hash = (hash * 33) + keyStr.charAt(i);
        }
        return hash % 1013;
    }
}
