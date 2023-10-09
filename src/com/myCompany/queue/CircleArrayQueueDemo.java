package com.myCompany.queue;

import java.util.Scanner;

/**
 * @author chenyaqi
 * @date 2021/4/3 - 9:14
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(3);
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
            System.out.println("n(head): 查看队列头的数据大小");

            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int num = scanner.nextInt();
                    circleArrayQueue.addQueue(num);
                    break;
                case 'g':
                    int n = 0;
                    try {
                        n = circleArrayQueue.getQueue();
                        System.out.println("得到数：" + n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    int res = 0;
                    try {
                        res = circleArrayQueue.headQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("得到队列头的数据：" + res);
                    break;
                case 'n':
                    int total = 0;
                    total = circleArrayQueue.size();
                    System.out.println("队列数据大小为：" + total);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

// 使用数组模拟队列-编写一个ArrayQueue类
class CircleArrayQueue {
    // 表示数组最大容量
    private int maxSize;

    // 队列头 : 指向队列的第一个元素，arr[front],初值为0
    private int front;

    // 队列尾 : 指向队列的最后一个元素的后一位置，初值为0
    private int real;

    // 该数组用于存放数据，模拟队列
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        this.arr = new int[this.maxSize];
        front = 0;
        real = 0;
    }

    // 判断队列是否满
    public boolean isFull() {
        return (real + 1) % maxSize == front;
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
            arr[real] = n;
            // real后移, 这里必须考虑取模
            real = (real + 1) % maxSize;
        }
    }

    // 获取队列数据
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空");
        } else {
            int tempVal =  arr[front];
            // front后移
            front = (front + 1)% maxSize;
            return tempVal;

        }
    }

    // 显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据！");
        } else {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){
        return (real + maxSize - front) % maxSize;
    }

    // 显示队列的头数据，不是取数据
    public int headQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列为空");
        } else {
            return arr[front];
        }
    }
}
