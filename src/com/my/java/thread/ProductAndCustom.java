package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */

/*
* 生产者/消费者问题:
* 生产者（Productor）将产品交给店员（Clerk），而消费者（Customer）从店员处取走产品，
* 店员一次只能持有固定数量的产品（比如：20），如果生产者试图生产更多的产品，店员会叫生产者停一下，
* 如果店中有空位放产品了再通知生产者继续生产；
* 如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。*/
public class ProductAndCustom {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Thread productor = new Thread(new Productor(clerk));
        Thread customer = new Thread(new Customer(clerk));

        productor.setName("生产者");
        customer.setName("消费者");

        productor.start();
        customer.start();
    }
}

class Clerk{

    private int productCount = 0;

    public synchronized void produceProduct() {
        if (productCount < 20){
            productCount++;
            notify();
            System.out.println(Thread.currentThread().getName() + "：开始生产第 " + productCount + " 个产品");
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void customProduct() {
        if (productCount > 0){
            System.out.println(Thread.currentThread().getName() + ": 开始消费第 " + productCount + " 个产品");
            productCount--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ：开始生产！");
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Customer implements  Runnable{

    private Clerk clerk;

    public Customer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 开始消费！");
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.customProduct();
        }
    }
}
