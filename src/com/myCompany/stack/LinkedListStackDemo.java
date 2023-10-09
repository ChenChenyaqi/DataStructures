package com.myCompany.stack;

/**
 * @author chenyaqi
 * @date 2021/4/4 - 21:31
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        

    }
}

// 用链表模拟栈
class LinkedListStack {
    private int maxSize;
    private Stack first = null;
    private Stack top = null;
    private int id = 2;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    // 判断栈是否满
    public boolean isFull() {
        if (top == null){
            return false;
        } else {
            return top.getId() == maxSize;
        }
    }

    // 判断栈是否空
    public boolean isEmpty() {
        return top == null;
    }

    // 入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        if (first == null) {
            first = new Stack(1);
            first.setValue(num);
            top = first;
            return;
        }
        top.setNext(new Stack(id));
        top.getNext().setValue(num);
        top.getNext().setPre(top);
        if (id == maxSize){
            id = 1;
        }
        id++;
        top = top.getNext();
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int value = top.getValue();
        if (top.getId() != 1){
            top = top.getPre();
            top.setNext(null);
        } else {
            first = null;
            top = null;
        }
        return value;
    }

    // 显示栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈已空");
        }
        while (true) {
            System.out.println(top.getValue());
            if (top.getPre() == null){
                break;
            }
            top = top.getPre();
        }
        while(true){
            if (top.getNext() == null){
                break;
            }
            top = top.getNext();
        }
    }
}

class Stack {
    private int id;
    private int value;
    private Stack next;
    private Stack pre;

    public Stack(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Stack getNext() {
        return next;
    }

    public void setNext(Stack next) {
        this.next = next;
    }

    public Stack getPre() {
        return pre;
    }

    public void setPre(Stack pre) {
        this.pre = pre;
    }
}