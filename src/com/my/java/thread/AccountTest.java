package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        Thread t1 = new Thread(account);
        Thread t2 = new Thread(account);

        t1.setName("A");
        t2.setName("B");

        t1.start();
        t2.start();
    }
}

class Account implements Runnable {

    private int balance = 0;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            addBalance(1000);
        }
    }

    private void addBalance(int num) {
        if (num > 0) {
            synchronized (this) {
                balance += num;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 存入1000元，剩余：" + balance);
            }
        }
    }
}
