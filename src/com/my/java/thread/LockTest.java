package com.my.java.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class LockTest {
    public static void main(String[] args) {
        Window3 w = new Window3();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window3 implements Runnable{

    private int ticket = 100;

    // 1.实例化一个ReentrantLock
    // 参数：fair,表示是否让线程遵循排队原则，先到先得
    private ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true){
            try {
                // 2.调用锁定lock方法相当于synchronized()，保证后面的代码是单线程的
                lock.lock();

                if (ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " : 售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 3.解锁
                lock.unlock();
            }
        }
    }
}
