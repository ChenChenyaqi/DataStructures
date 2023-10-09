package com.my.java.thread;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        // 3. 创建实现类的对象
        MyThread2 myThread2 = new MyThread2();

        // 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(myThread2);
        Thread t2 = new Thread(myThread2);

        t1.setName("线程1");
        t2.setName("线程2");

        //5. 通过Thread类的对象调用start()
        t1.start();
        t2.start();
    }
}

// 1. 创建一个实现了Runnable接口的类
class MyThread2 implements Runnable{

    // 2. 实现类去实现Runnable中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}
