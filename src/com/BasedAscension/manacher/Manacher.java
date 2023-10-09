package com.BasedAscension.manacher;

/**
 * Manacher算法
 * <p>
 * 字符串str中，最长回文子串的长度如何求解？
 * 经典解法，在每个字符两边加上特殊字符 '#'(原串中有没有无所谓)，分别以 i 位置的字符为中心轴，左右向两边扩，得出的结果 / 2
 */
public class Manacher {

    // 塞 '#' 的过程
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = manacherString(s); // 123 -> #1#2#3#
        int[] pArr = new int[str.length];  // 回文半径数组
        int C = -1; // 中心
        int R = -1; // 回文左边界的再往右一个位置  最右的有效区是 R-1 位置
        int max = Integer.MIN_VALUE; // 扩出来的最大值
        for (int i = 0; i < str.length; i++) {  // 每一个位置都求回文半径
            // i 至少的回文区域(不用验的区域)，先给pArr[i]
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            // 都往外扩扩试试
            while (i + pArr[i] < str.length && i - pArr[i] > 1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }
}
