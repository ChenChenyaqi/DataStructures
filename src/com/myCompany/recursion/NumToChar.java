package com.myCompany.recursion;

/**
 * 给一串数字字符串，返回把数字对应成字母，共有多少种不同的对应结果
 * 如："111" -> "AAA" / "AK" / "KA"  3种
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class NumToChar {
    public static void main(String[] args) {
        String nums = "1112";
        int count = process(nums.toCharArray(), 0);
        System.out.println(count);
    }

    // i 之前的位置，如何转化已经做过决定了
    // i 以及 i之后有多少种决定方式
    private static int process(char[] str, int i) {
        // 到达末尾，表示完成了一组转化
        if (i == str.length) {
            return 1;
        }
        // 没法转化0
        if (str[i] == '0') {
            return 0;
        }
        // 要么i自己转，要么i和i+1组合起来转
        if (str[i] == '1') {
            // i自己转，统计后面有多少种组合
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                // i与i+1组合
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        // 对于str[i] >= '3' 的，都只能自己转
        return process(str, i+1);
    }
}
