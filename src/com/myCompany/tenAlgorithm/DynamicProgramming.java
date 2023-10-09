package com.myCompany.tenAlgorithm;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/5/20 - 15:56
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        // w个工人去挖n座金矿，问最多可以采集多少黄金，每座金矿黄金数不同，需要的工人也不同
        // n为金矿数
        int n = 5;
        // w为工人数
        int w = 10;
        // g为每座金矿有多少黄金
        int[] g = {200, 400, 300, 350, 500};
        // p为每座金矿依次需要多少工人
        int[] p = {3, 5, 4, 3, 5};
        System.out.println("工人数:\t\t  0  1  2    3    4     5    6    7    8    9    10");
        int mostGold = getMostGold(n, w, g, p);
        System.out.println("mostGold = " + mostGold);


        // 一个背包容量为4磅, 有吉他G 重量1 价值1500；音响S 重量4 价值3000；电脑L 重量3 价值 2000, 求最大价值及组合
        int bagCap = 4;
        int thingNum = 3;
        int[] thingWight = {1,4,3};
        int[] thingValue = {1500,3000,2000};
        int mostValue = getMostValue(bagCap, thingNum, thingWight, thingValue);
        System.out.println("mostValue = " + mostValue);

    }

    public static int getMostGold(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[w + 1];
        int[] results = new int[w + 1];

        // 填充边界格子的值（工人数从0到10）
        for (int i = 0; i < w + 1; i++) {
            if (i < p[0]) {
                preResults[i] = 0;
            } else {
                preResults[i] = g[0];
            }
        }
        System.out.println("preResults = " + Arrays.toString(preResults));

        // 填充其余格子的值，外层循环是金矿数量，内层循环是工人数
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (j < p[i - 1]) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p[i - 1]] + g[i - 1]);
                }
            }
            preResults = Arrays.copyOf(results, w + 1);
            System.out.println("preResults = " + Arrays.toString(preResults));
        }
        return results[w];
    }

    public static int getMostValue(int bagCap, int thingNum, int[] thingWight, int[] thingValue){
        int[] preResults = new int[bagCap + 1];
        int[] results = new int[bagCap + 1];

        for (int i = 0; i < bagCap + 1; i++) {
            if (i < thingWight[0]){
                preResults[i] = 0;
            } else {
                preResults[i] = thingValue[0];
            }
        }
        System.out.println("preResults = " + Arrays.toString(preResults));
        for (int i = 2; i <= thingNum; i++) {
            for (int j = 0; j < bagCap + 1; j++) {
                if (j < thingWight[i - 1]){
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - thingWight[i - 1]] + thingValue[i - 1]);
                }
            }
            preResults = Arrays.copyOf(results,bagCap+1);
            System.out.println("preResults = " + Arrays.toString(preResults));
        }
        return results[bagCap];
    }
}
