package com.BasedAscension.KMP;

/**
 * KMP算法
 * <p>
 * 求子串问题，str2 是不是 str1 的子串
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "abcd123gedabcde";
        String str2 = "1";
        System.out.println(getIndexOf(str1, str2));
    }

    // 返回 m 在 s 中的位置
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (i2 == 0) {  // next[i2] == -1 str2中比对的i2已经无法往前跳了
                i1++;
            } else {
                // 直接来到 next[i2]位置
                // 假如前缀长度0~6
                // next[i2] = 7，则就来到了前缀的下一个字符
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if(ms.length == 1){
            return new int[]{-1};
        }
        // next数组，规定 0位置：-1, 1位置：0
        // next[i]: i 之前 前缀和后缀匹配的最大长度
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;  // next 数组的位置
        int cn = 0; // 当前要跟 i - 1 位置比对的字符, 还表示 当前next[i - 1]的值
        while(i < next.length){
            // 如果前缀下一个字符 == i-1 位置的字符，则next[i] = next[i - 1] + 1
            if(ms[cn] == ms[i - 1]){
                cn++;
                next[i] = cn;
                i++;
            } else if(cn > 0){ // 当匹配不上时
                // cn往前跳
                cn = next[cn];
            } else {
                // cn没法往前跳了
                next[i] = 0;
                i++;
            }
        }
        return next;
    }
}
