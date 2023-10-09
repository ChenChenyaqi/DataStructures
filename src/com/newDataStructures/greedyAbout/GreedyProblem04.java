package com.newDataStructures.greedyAbout;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正数数组 costs
 * 正数数组 profits
 * 正数 k
 * 正数 w
 * costs[i] 表示 i 号项目的花费
 * profits[i] 表示 i 号项目在扣除花费之后还能挣到多少钱（利润）
 * k 表示你只能串行的最多做 k 个项目
 * w 表示你初始的资金
 * <p>
 * 输出：最大的获得的钱数
 * <p>
 * 用两个堆，小根堆存储 花费 最小的，大根堆存储 利润 最大的
 */
public class GreedyProblem04 {

    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int problem04(int k, int W, int[] profits, int[] cost) {
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Node(profits[i], cost[i]));
        }
        for (int i = 0; i < k; i++) {
            // 能力所及的项目全解锁
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                break;
            }
            W += maxProfitQ.poll().p;
        }
        return W;

    }
}
