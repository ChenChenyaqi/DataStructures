package com.intermediateClass.lesson4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 咖啡杯问题（负载均衡）
 *
 * 一个正数组成的数组 arr
 * 里面每个数，代表某个咖啡机需要多长时间做一杯咖啡
 * 给你一个数 N 代表有多少人要喝咖啡，每个人只喝一杯
 * 所有人喝咖啡的时间为 0
 * 第三个参数 a ，只有一台洗咖啡的机器，洗一杯需要 a 时间，只能串行不能并行
 * 第四个参数 b，咖啡杯不洗，自然变干净的时间
 *
 * 问这堆人从泡咖啡开始，直到所有咖啡变干净，至少需要多少时间？
 *
 */
public class CoffeeCup {

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,7};
        int N = 20;
        System.out.println(coffee(arr, N, 2, 10));
    }

    // 先看所有人怎么冲咖啡（利用小根堆）
    // 小根堆存的数据是一个长度为2的数组
    // [0]: 开始空闲时间
    // [1]: 工作时间（初始arr中的数）
    // 最开始，[0] 位置的数都是 0，表示从0时刻开始空闲
    // 一个人使用一个咖啡机泡咖啡后，[0] += [1] ，开始空闲时间往后推
    // 排序策略：[0] + [1] 最小的在上面

    // 洗的时候：
    // 得到冲咖啡的时间，就是洗的时间
    // 从左往右的尝试模型
    public static int coffee(int[] arr, int N,int a, int b){
        int[] arr1 = getCoffee(arr, N);
        return clearCoffee(arr1, a, b, 0, 0);
    }

    // 洗咖啡或自然干净
    // washLine：咖啡机什么时刻有空
    // 如果要洗完arr1[index...N-1],返回最少的时间
    private static int clearCoffee(int[] drinks, int a, int b, int index, int washLine) {
        if(index == drinks.length - 1){
            // 洗咖啡 和 自然晾干 中最小的那个
            // drinks[index]: 开始洗的时刻， 如果我没喝完，没法用咖啡机洗
            // washLine：咖啡机空闲的时刻
            return Math.min(Math.max(washLine, drinks[index]) + a, drinks[index] + b);
        }
        // wash是我当前的咖啡杯，决定放在咖啡机里洗，什么时候洗完
        int wash = Math.max(washLine, drinks[index])  + a;
        // 洗完剩下所有的咖啡杯，最早的结束时间
        int next1 = clearCoffee(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        // dry是我决定不洗，晾干的时间
        int dry = drinks[index] + b;
        int next2 = clearCoffee(drinks, a, b, index + 1, dry);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    // 冲咖啡的安排
    private static int[] getCoffee(int[] arr, int N) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new MyComparator());
        // 初始化
        for (int i = 0; i < arr.length; i++) {
            // 从 0 时刻开始空闲，arr[i]工作时间
            queue.add(new Integer[]{0, arr[i]});
        }
        // [i]表示，第 i 个人能什么时候可以拿到咖啡
        int[] res = new int[N];
        // N 个人冲咖啡
        for (int i = 0; i < N; i++) {
            Integer[] t = queue.poll();
            res[i] = t[0] + t[1];
            t[0] += t[1];
            queue.add(t);
        }
        return res;
    }

    public static class MyComparator implements Comparator<Integer[]>{

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return -(o2[0] + o2[1] - o1[0] - o1[1]);
        }
    }


}
