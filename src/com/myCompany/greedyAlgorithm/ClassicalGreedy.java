package com.myCompany.greedyAlgorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 经典的贪心算法
 *  一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 *  给你每一个项目 开始的时间 和 结束的时间（给你一个数组，里面是一个个具体
 *  的项目），你来安排宣讲的日程，要求会议室进行的宣讲的 场次最多 。
 *  返回这个 最多的宣讲场次
 * @author Chen Yaqi
 * @version 1.0
 */
public class ClassicalGreedy {

    /**
     * 要宣讲的项目
     */
    public static class Program{
        // 开始时间
        public double startTime;
        // 结束时间
        public double endTime;

        public Program(double startTime, double endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * 按 结束时间 比较 Program
     */
    public static class TimeComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            if (o1.endTime > o2.endTime){
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * 返回最多的宣传次数
     * @param programs 所有项目
     * @return 最大宣传次数
     */
    public static int maxCount(Program[] programs){
        // 按照结束时间，从早到晚对项目进行排序
        Arrays.sort(programs,new TimeComparator());
        int res = 1;
        double endTime = programs[0].endTime;
        for (int i = 1; i < programs.length; i++) {
            if (programs[i].startTime >= endTime){
                res++;
                endTime = programs[i].endTime;
            }
        }
        return res;
    }

    @Test
    public void test(){
        Program[] programs = new Program[5];
        programs[0] = new Program(7.5, 8);
        programs[1] = new Program(9.5, 10);
        programs[2] = new Program(7.3, 9);
        programs[3] = new Program(6, 7.5);
        programs[4] = new Program(8.5, 9);

        int maxCount = maxCount(programs);
        System.out.println("maxCount = " + maxCount);
    }
}
