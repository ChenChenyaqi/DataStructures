package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        // 3.创建Thread类的子类对象
        MyThread t1 = new MyThread();
        Thread.currentThread().setName("线程1");
        t1.setName("线程2");

        t1.setPriority(10);
        Thread.currentThread().setPriority(2);
        System.out.println("线程1 ：" + Thread.currentThread().getPriority());
        System.out.println("线程2 ： " + t1.getPriority());
        // 4.通过此对象调用start()  作用：1、启动当前线程2、调用当前线程的run()
        t1.start();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            if (i == 20){
                t1.join();
            }
        }
    }
}

// 1.创建一个继承于Thread类的子类
class MyThread extends Thread{
    // 2.重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            if (i == 20){
                yield();
            }
        }
    }
}
