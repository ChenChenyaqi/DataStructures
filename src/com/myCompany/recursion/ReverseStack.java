package com.myCompany.recursion;

import java.util.Stack;

/**
 * 把一个栈逆序
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);
        System.out.println(stack.toString());
        reverse(stack);
        System.out.println(stack.toString());
    }

    // 逆序栈
    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()){
            return;
        }
        int i = getLast(stack);
        reverse(stack);
        stack.push(i);
    }

    // 弹出栈底的元素并返回
    private static int getLast(Stack<Integer> stack) {
        int res = stack.pop();
        if (stack.isEmpty()){
            return res;
        }
        int last = getLast(stack);
        stack.push(res);
        return last;
    }
}
