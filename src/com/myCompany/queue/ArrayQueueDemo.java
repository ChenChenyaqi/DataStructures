package com.myCompany.queue;

import java.util.Scanner;

/**
 * @author chenyaqi
 * @date 2021/4/2 - 19:15
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到对列");
            System.out.println("g(get): 从队列中取数据");
            System.out.println("h(head): 查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int num = scanner.nextInt();
                    arrayQueue.addQueue(num);
                    break;
                case 'g':
                    int n = 0;
                    try {
                        n = arrayQueue.getQueue();
                        System.out.println("得到数：" + n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    int res = 0;
                    try {
                        res = arrayQueue.headQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("得到队列头的数据：" + res);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {
    // 表示数组最大容量
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int real;
    // 该数组用于存放数据，模拟队列
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        // 指向队列头部，是指向队列头的前一个位置
        front = -1;
        // 指向队列尾部，是指向队列尾的数据（即就是队列最后一个数据）
        real = -1;
    }

    // 判断队列是否满
    public boolean isFull() {
        return real == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return real == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据！");
        } else {
            // real后移
            real++;
            arr[real] = n;
        }
    }

    // 获取队列数据
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空");
        } else {
            // front后移
            front++;
            return arr[front];
        }
    }

    // 显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据！");
        } else {
            for (int i = 0; i < maxSize; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }
    }

    // 显示队列的头数据，不是取数据
    public int headQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空");
        } else {
            return arr[front + 1];
        }
    }
}
