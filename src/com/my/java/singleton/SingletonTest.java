package com.my.java.singleton;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class SingletonTest {
    public static void main(String[] args) {
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();

        // 是同一个对象
        System.out.println(bank1 == bank2);

        bank1.num = 20;
        System.out.println("bank1 = " + bank1.num);
        System.out.println("bank1 = " + bank2.num);

        Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();

        System.out.println(order1 == order2);
        // 懒汉式和饿汉式对比：
        // 饿汉式：
        //      好处：饿汉式是线程安全的
        //      坏处：对象加载时间过长
        // 懒汉式：
        //      好处：延迟对象的创建
        //      坏处：线程不安全
    }
}

// 饿汉式
class Bank {
    // 1.私有的构造器
    private Bank() {

    }

    // 2.内部创建类的对象
    // 4.要求此对象也是静态的
    private static Bank instance = new Bank();

    public int num;

    // 3.提供公共的静态的方法，返回类的对象
    public static Bank getInstance() {
        return instance;
    }
}

// 懒汉式
class Order {
    private Order() {

    }

    private static Order instance;

    // 添加synchronized关键词使Order变成同步方法
    public static Order getInstance() {
        // 此处线程不安全，有可能多个线程同时进入if语句，分别new了个对象
        if (instance == null) {
            synchronized (Order.class) {
                if (instance == null) {
                    instance = new Order();
                }
            }
        }
        return instance;
    }
}
