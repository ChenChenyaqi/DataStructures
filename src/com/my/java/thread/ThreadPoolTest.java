package com.my.java.thread;

import java.util.concurrent.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1.创建指定数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service = (ThreadPoolExecutor)executorService;
        // 线程池的管理
        service.setCorePoolSize(15);
        // 2.提供相应的接口对象
        NumTest numTest = new NumTest();

        NumTest2 numTest2 = new NumTest2();
        FutureTask futureTask = new FutureTask(numTest2);

        // 3.执行线程
        service.execute(numTest); // 适用于Runnable
        Future<?> submit = service.submit(futureTask);// 适用于Callable
        try {
            Object sum = submit.get();
            System.out.println("和为：" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 4.关闭线程池
        service.shutdown();
    }
}

class NumTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}

class NumTest2 implements Callable{

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                sum+=i;
            }
        }
        return sum;
    }
}
