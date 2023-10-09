package com.intermediateClass.lesson5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 给你一个类Job
 * 一个Job[] jobarr, 表示所有工作
 * 一个int[] arr, 表示所有小伙伴的能力
 * 返回 int[] ，表示每个小伙伴所能获得的报酬
 */
public class FindJob {

    public static class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    /**
     * 先按 难度 递增 排序
     * 相同难度为一组，每个组里跳一个报酬最大的作为这个组的组长
     * 按照 报酬 递增，依次看每个组长，如果出现一个组长 报酬 减小了，则直接抛弃这个组
     *
     * @param jobArr 工作
     * @param ability 能力
     * @return 结果
     */
    public static int[] jobs(Job[] jobArr, int[] ability) {
        // 按难度从小到大，报酬从大到小的顺 序排序
        Arrays.sort(jobArr, new JobComparator());
        // 难度为key的工作，最优的钱数是多少，有序表
        // key 如果递增，value一定递增
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobArr[0].hard, jobArr[0].money);
        Job pre = jobArr[0]; // pre 之前组的组长
        for (int i = 1; i < jobArr.length; i++) {
            if(jobArr[i].hard != pre.hard && jobArr[i].money > pre.money){
                pre = jobArr[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // 返回hard小于等于 ability[i] 中money的最大值
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }

    private static class JobComparator implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }
}
