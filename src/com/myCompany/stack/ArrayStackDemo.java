package com.myCompany.stack;

/**
 * @author chenyaqi
 * @date 2021/4/4 - 21:12
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }
}

// 定义一个ArrayStack表示栈
class ArrayStack{
    // 栈的大小
    private int maxSize;
    // 数组模拟栈
    private int[] stack;
    // top表示栈顶，初始化为-1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈是否空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(int value){
        // 先判断栈是否满
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop(){
        // 先判断是否空
        if (isEmpty()){
            // 抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈（遍历栈）
    // 遍历时，需要从栈顶开始显示
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
