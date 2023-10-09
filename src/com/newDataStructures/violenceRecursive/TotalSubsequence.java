package com.newDataStructures.violenceRecursive;

import java.util.List;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 */
public class TotalSubsequence {


    // 当前来到 i 位置，要和不要 两条路
    // res 是之前的选择，所形成的列表
    public static void process(char[] str, int i , List<Character> res){
        if( i == str.length){
            printList(res);
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process(str, i+1, resKeep); // 要当前字符的路
        List<Character> resNoInclude = copyList(res);
        process(str, i + 1, resNoInclude); // 不要当前字符的路

    }

    private static List<Character> copyList(List<Character> res) {
        return null;
    }


    private static void printList(List<Character> res) {
    }


    public static void main(String[] args) {
        String str = "abc";
        process2(str.toCharArray(), 0);
    }

    // 省空间的做法
    // 之前的选择，所形成的结果，是 str，会更改的
    public static void process2(char[] str, int i){
        if(i == str.length){
            System.out.println(String.valueOf(str));
            return;
        }
        process2(str, i + 1); // 要当前字符的路
        char temp = str[i];
        str[i] = 0;
        process2(str, i + 1); // 不要当前字符的路
        str[i] = temp;
    }
}
