package com.newDataStructures.greedyAbout;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法问题01
 * 一个项目要占用一个会议室进行宣讲，会议室不能同时容纳两个项目的宣讲
 * 给你每一个项目开始的时间和结束的时间（给你一个数组，里面是一个个具体的项目）
 * 你来安排宣讲的日程，要求会议室进行宣讲的场次最多。返回这个最多的宣讲次数
 */
public class GreedyProblem01 {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }


    // 以会议  结束时间 最早 优先
    public int problem01(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if(timePoint <= programs[i].start){
                result++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }
}
