package com.newDataStructures.violenceRecursive;

import java.util.ArrayList;

/**
 * 字符串全排列
 */
public class WholeArrangement {

    public static void main(String[] args) {
        String str = "aba";
        ArrayList<String> res = new ArrayList<>();
        process(str.toCharArray(), 0, res);
        System.out.println(res);
        res = new ArrayList<>();
        process2(str.toCharArray(), 0, res);
        System.out.println(res);
    }


    // str[i...] i 后面每个元素都可以来到i位置
    // str[0,...,i - 1] i 之前代表已经选择好的字符
    public static void process(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j); // j 来到 i 位置
            process(str, i + 1, res);
            swap(str, i, j); // j 离开 i 位置
        }
    }


    public static void fun(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        boolean[] flag = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!flag[str[j] - 'a']) {
                flag[str[j] - 'a'] = true;
                swap(str, i, j);
                fun(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }


    // 去重
    public static void process2(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        // 在当前 i 位置，如果有一个字符来过了，则不能再次来到 i
        boolean[] visited = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visited[str[j] - 'a']) {
                visited[str[j] - 'a'] = true;
                swap(str, i, j);
                process2(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }
}
