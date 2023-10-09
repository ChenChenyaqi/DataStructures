package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class CommunicationTest {
    public static void main(String[] args) {
        Num num = new Num();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);

        t1.start();
        t2.start();

    }
}

class Num implements Runnable {

    private int number = 1;

    @Override
    public void run() {
        while (true) {
            // notify()和wait()方法的调用者必须是同步监视器
            synchronized (this) {

                // 唤醒线程
                notify();

                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        // 使得调用wait()方法的线程进入阻塞，会释放锁，sleep不会
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}
