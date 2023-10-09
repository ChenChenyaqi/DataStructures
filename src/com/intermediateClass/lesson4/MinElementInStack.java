package com.intermediateClass.lesson4;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在栈的功能之前，再实现一个可以返回栈中最小元素的操作
 */
public class MinElementInStack {

    public static class MyStack {
        public Stack<Integer> dataStack;
        public Stack<Integer> minStack;

        public MyStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int num) {
            dataStack.push(num);
            if (minStack.peek() < num) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(num);
            }
        }

        public int getMin() {
            return minStack.peek();
        }

        public int pop() {
            minStack.pop();
            return dataStack.pop();
        }
    }
}
