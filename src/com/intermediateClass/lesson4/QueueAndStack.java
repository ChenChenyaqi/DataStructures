package com.intermediateClass.lesson4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 如何仅用队列结构实现栈结构？
 * 如何仅用栈结构实现队列结构？
 *
 * 扩展：面试官让你用 队列 实现图的深度优先遍历， 用 栈 实现树的层序遍历
 */
public class QueueAndStack {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        stack.push(5);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

    public static class MyQueue{
        public Stack<Integer> pushStack;
        public Stack<Integer> popStack;

        public MyQueue(){
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void push(int num){
            pushStack.push(num);
        }

        public int pop(){
            if(popStack.isEmpty()){
                while(!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
        }
    }

    public static class MyStack{
        public Queue<Integer> queue1;
        public Queue<Integer> queue2;

        public MyStack(){
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int num){
            Queue<Integer> q1 = null;
            if(queue1.isEmpty() && queue2.isEmpty()){
                q1 = queue1;
            } else {
                q1 = queue1.isEmpty() ? queue2 : queue1;
            }
            q1.add(num);
        }

        public Integer pop(){
            Queue<Integer> q1 = null;
            Queue<Integer> q2 = null;
            if(queue1.isEmpty() && queue2.isEmpty()){
                return null;
            } else {
                q1 = queue1.isEmpty() ? queue2 : queue1;
                q2 = q1 == queue1 ? queue2 : queue1;
            }
            while(q1.size() != 1){
                q2.add(q1.poll());
            }
            return q1.poll();
        }
    }
}
