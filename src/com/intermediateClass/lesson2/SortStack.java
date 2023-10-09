package com.intermediateClass.lesson2;

import java.util.Stack;

/**
 * 对一个栈里的整形数据，按升序进行排序（排序前，栈里的数据是无序的，排序后最大元素位于栈顶）
 * 要求最多使用一个额外的栈存放临时数据
 */
public class SortStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(9);
        stack.push(1);
        stack.push(6);
        stack.push(4);
        stack.push(7);
        process(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

    public static void process(Stack<Integer> stack){
        // helpStack里的数据 要求 从大到小 栈顶是最小的
        Stack<Integer> helpStack = new Stack<>();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            if (helpStack.isEmpty()){
                helpStack.push(cur);
            } else if(helpStack.peek() < cur) {
                while(!helpStack.isEmpty() && helpStack.peek() < cur){
                    stack.push(helpStack.pop());
                }
                helpStack.push(cur);
            } else {
                helpStack.push(cur);
            }
        }
        while(!helpStack.isEmpty()){
            stack.push(helpStack.pop());
        }
    }
}
