package com.myCompany.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出一个字符串的所有子串, 包括空字符串
 * @author Chen Yaqi
 * @version 1.0
 */
public class AllSubstrings {
    public static void main(String[] args) {
        String str = "ab";
        String[] res = getAllSubStrings(str);
        for (String s : res){
            System.out.println(s);
        }
        System.out.println(res.length);
    }

    private static String[] getAllSubStrings(String str) {
        char[] chars = str.toCharArray();
        List<String> resList = new ArrayList<>();
        process(chars, resList, 0);
        String[] res = new String[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private static void process(char[] chars, List<String> res, int i) {
        if (i == chars.length){
            res.add(String.valueOf(chars));
            return;
        }
        process(chars, res, i+1);
        char temp = chars[i];
        chars[i] = 0;
        process(chars, res, i+1);
        chars[i] = temp;
    }
}
