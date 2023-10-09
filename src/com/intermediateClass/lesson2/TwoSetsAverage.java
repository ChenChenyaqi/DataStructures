package com.intermediateClass.lesson2;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给你俩集合a，b，一个包含 n 个整数，另一个包含 m 个整数
 * 你可以从一个集合中拿出一个数，放到另一个集合中，使得两个集合的平均值 都 大于 操作前
 * 不可以拿空一个集合
 * 问最多有多少次操作？
 *
 * 分析：只能从 平均值大 的集合中，到 平均值小的集合中，且 小 < 拿掉的数 < 大
 * 在满足条件的数里，拿 最小的数 给 小集合，这样会使操作次数 尽可能多
 */
public class TwoSetsAverage {

    // 请保证 arr1无重复值，arr2无重复值，且arr1和arr2肯定有数字
    public static int maxOps(int[] arr1, int[] arr2){
        double sum1 = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum1 += arr1[i];
        }
        double sum2 = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum2 += arr2[i];
        }
        if(avg(sum1, arr1.length) == avg(sum2, arr2.length)){
            return 0;
        }
        // 平均值不同
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        if(avg(sum1, arr1.length) >avg(sum2,arr2.length)){
            arrMore = arr1;
            sumMore = sum1;
            arrLess = arr2;
            sumLess = sum2;
        } else {
            arrMore = arr2;
            sumMore = sum2;
            arrLess = arr1;
            sumLess = sum1;
        }
        Arrays.sort(arrMore);
        HashSet<Integer> setLess = new HashSet<>();
        for (int num : arrLess){
            setLess.add(num);
        }
        int moreSize = arrMore.length;
        int lessSize = arrLess.length;
        int ops = 0; // 操作了多少次
        for (int i = 0; i < arrMore.length; i++) {
            double cur = arrMore[i];
            if(cur < avg(sumMore, moreSize) && cur > avg(sumLess, lessSize) && !setLess.contains(arrMore[i])){
                sumMore -= cur;
                moreSize--;
                sumLess += cur;
                lessSize++;
                setLess.add(arrMore[i]);
                ops++;
            }
        }
        return ops;
    }

    private static double avg(double sum, int length) {
        return sum / length;
    }
}
