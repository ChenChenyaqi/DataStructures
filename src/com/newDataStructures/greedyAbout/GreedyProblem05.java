package com.newDataStructures.greedyAbout;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中，随时可以取得中位数
 *
 * 堆的应用
 *
 * 1.第一个数，直接入大根堆
 * 2.当前数字与大根堆堆顶比较：cur <= peek ? cur 入大根堆 : cur 入小根堆
 * 3.看看大根堆和小根堆的大小， size较大 - size较小 == 2, 较大的堆顶 弹出 入较小的
 *
 * 效果，较小的那些数(1/2)都在小根堆，较大的那些数(1/2)都在大根堆，中位数 = (peek1 + peek2) / 2.0
 */
public class GreedyProblem05 {
    public static class MinStackComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    public static class MaxStackComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,3,1,6,2,5};
        System.out.println(getMidNum(nums));
    }

    public static double getMidNum(int[] nums){
        PriorityQueue<Integer> minQ = new PriorityQueue<>(new MinStackComparator());
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxStackComparator());
        double res = 0;
        maxQ.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] <= maxQ.peek()){
                maxQ.add(nums[i]);
            } else{
                minQ.add(nums[i]);
            }
            if(maxQ.size() - minQ.size() == 2){
                minQ.add(maxQ.poll());
            }
            if(minQ.size() - maxQ.size() == 2){
                maxQ.add(minQ.poll());
            }
        }
        if(minQ.size() > maxQ.size()){
            return minQ.peek();
        }
        if(maxQ.size() > minQ.size()){
            return maxQ.peek();
        }
        return (minQ.peek() + maxQ.peek()) / 2.0;
    }

}
