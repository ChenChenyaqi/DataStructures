package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w = new Window2();

        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("1号");
        w2.setName("2号");
        w3.setName("3号");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window2 implements Runnable {

    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            synchronized(this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 窗口：出售票号 " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
