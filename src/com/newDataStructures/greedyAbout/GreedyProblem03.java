package com.newDataStructures.greedyAbout;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你 一块黄金， 一个数组，数组里是要切成多少份，每份多少大小
 * 每切一次，就要花费和此刻黄金长度等同的铜币
 * 求最小的代价
 *
 * 哈弗曼编码问题
 */
public class GreedyProblem03 {

    public static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static int problem03(Integer totalLen, int[] nums){
        PriorityQueue<Integer> queue = new PriorityQueue<>(new MyComparator());
        for(int n : nums){
            queue.add(n);
        }
        int sum = 0;
        int cur = 0;
        while(queue.size() > 1){
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }
}
