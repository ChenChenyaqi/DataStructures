package com.myCompany.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenyaqi
 * @date 2021/5/3 - 14:47
 */
public class DefaultQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
    }
}
