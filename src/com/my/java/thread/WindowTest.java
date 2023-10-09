package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class WindowTest {
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();

        window1.setName("1号");
        window2.setName("2号");
        window3.setName("3号");

        window1.start();
        window2.start();
        window3.start();
    }
}

class Window extends Thread {
    private static int ticket = 100;
    private static final Object object = new Object();
    @Override
    public void run() {
        while (true) {
//            synchronized (object) {
            synchronized(Window.class){
                if (ticket > 0) {
                    System.out.println(getName() + " 窗口：出售票号 " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
