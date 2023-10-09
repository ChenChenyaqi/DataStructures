package com.myCompany.recursion;

import java.util.LinkedList;

/**
 * 对一个字符串中的所有字符进行全排列
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class WholeArrangement {
    public static void main(String[] args) {
        String str = "abc";
        char[] chars = str.toCharArray();
        boolean[] used = new boolean[chars.length];
        wholeArrangement(chars, used, 0, new LinkedList<>());
    }

    private static void wholeArrangement(char[] chars, boolean[] used, int depth, LinkedList<Character> queue) {
        if (depth == chars.length){
            System.out.println(queue.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]){
                queue.add(chars[i]);
                used[i] = true;
                wholeArrangement(chars,used,depth+1,queue);
                used[i] = false;
                queue.removeLast();
            }
        }
    }
}
