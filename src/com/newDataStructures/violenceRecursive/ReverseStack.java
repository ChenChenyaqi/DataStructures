package com.newDataStructures.violenceRecursive;

import java.util.Stack;

/**
 * 逆序一个栈
 */
public class ReverseStack {

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        // f 返回栈底元素并弹出
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        } else{
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
