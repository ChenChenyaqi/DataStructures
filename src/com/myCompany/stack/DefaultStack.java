package com.myCompany.stack;

import java.util.Stack;

/**
 * @author chenyaqi
 * @date 2021/5/3 - 14:30
 */
public class DefaultStack {
    public static void main(String[] args) {
        String str = "www.w3cschool.cc";
        Stack<Character> stack = new Stack<>();

        char[] chars = str.toCharArray();
        for (char i : chars){
            stack.add(i);
        }
        char[] chars1 = new char[stack.size()];
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            chars1[i] = stack.pop();
        }
        System.out.println(new String(chars1));
    }
}
