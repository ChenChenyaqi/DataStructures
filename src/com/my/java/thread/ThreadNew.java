package com.my.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class ThreadNew {
    public static void main(String[] args) {
        // 3.创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        // 4.将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(numThread);
        // futureTask实现了Runnable接口
        // 5.将futureTask作为参数传递到Thread的构造器中并调用start方法
        new Thread(futureTask).start();
        try {
            // 6.get()方法得到call()的返回值
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

// 1.创建一个实现Callable的实现类
class NumThread implements Callable<Integer>{

    // 2.实现call方法
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(i);
                sum+=i;
            }
        }
        return sum;
    }
}

