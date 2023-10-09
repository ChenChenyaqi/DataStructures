package com.newDataStructures.violenceRecursive;

/**
 * 规定 1 对应 A， 2 对应 B， 3 对应 C ....
 * 比如 "111" -> "AAA" 、"KA"、"AK"
 * 问，有多少种结果
 * <p>
 * 从左往右试
 */
public class NumberToChar {

    public static void main(String[] args) {
        String str = "111";
        System.out.println(numberTOChar(str.toCharArray(), 0));
    }

    // 0  到 i - 1， 已经决定好了，不能改变了，现在考虑 i 位置
    // 如果 str[i] == 0 , 不能转化
    // 如果  0 < str[i] < 2, 要么跟下个字符去结合起来，要么自己单独转化
    // 如果 str[i] > 2, 只能自己单独转化
    public static int numberTOChar(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = numberTOChar(str, i + 1);
            if (i + 1 < str.length) {
                res += numberTOChar(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = numberTOChar(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += numberTOChar(str, i + 2);
            }
            return res;
        }
        // str[i] == '3' ~ '9'
        return numberTOChar(str, i + 1);
    }

    // 化为dp
    public static int dpWays(int num){
        if(num < 1){
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        dp[N - 1] = str[N - 1] == '0' ? 0 : 1;
        for (int i = N - 2; i >= 0; i--) {
            if(str[i] == '0'){
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1]
                        + (((str[i] - '0') * 10 +str[i + 1] - '0') < 27 ? dp[i + 2] : 0);
            }
        }
        return dp[0];
    }
}
