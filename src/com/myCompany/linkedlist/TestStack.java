package com.myCompany.linkedlist;

import java.util.Stack;

/**
 * @author chenyaqi
 * @date 2021/4/4 - 10:12
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        // 出栈
        while (stack.size()> 0){
            // pop就是将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }
}
