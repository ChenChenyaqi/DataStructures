package com.intermediateClass.lesson2;

/**
 * 一个完整的括号字符串定义如下：
 * 1、空字符串是完整的
 * 2、如果 s 是完整的字符串，那么(s)也是完整的
 * 3、如果 s 和 t 是完整的字符串，将它们连接起来形成的 st 也是完整的
 *
 * 例如，"(() ())", "", "(()) ()"是完整的括号字符串
 *
 * 牛牛有一个括号字符串s，现在需要在其中任意位置尽量少的添加括号
 * 将其转化为完整的括号字符串。问至少需要添加多少个括号？
 *
 * 解法：一个指针从左往右遍历。遇见"(" count++， 遇见")" count--
 * 1.若count < 0 则 res++, count变为0 （至少需要多少左括号）
 * 2.当结束遍历后，count == 0 ， 若 > 0， 则 res += count（还需要多少右括号）
 */
public class CompleteBrackets {

    /**
     * 题目二：合法括号匹配的深度
     * 1、如果是空串，深度为0
     * 2、如果"X"深度为 x， "Y"深度为 y， 则"XY"深度为 max(x,y)
     * 3、如果"X"深度为 x， 则"(X)"深度 为 x + 1
     *
     *  计算一个序列的深度
     *
     *  上题，count达到的最大值就是最大深度
     */

    /**
     * 题目三：找到 最长的 连续 有效 括号 子串
     *
     * 解法：求以每个字符结尾的，最长子串长度
     */
    public static int process(String s){
        // dp[i] 表示，子串必须以 i 位置结尾，最长的有效长度是多少？
        // dp[i] 的值，看 dp[i - 1]的值 + 如果 dp[i-1]的长度再往前一个（p位置） 是否为 '('
        //              若是：dp[i] = dp[i - 1] + 2 + dp[p - 1]，否则：dp[i] = 0

        // 如：(  (  )  (  )  )  (  )  (   )
        //     0  0  2  0  4  6  0  8  0  10

        if(s == null || s.equals("")){
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < str.length; i++) {
            if(str[i] == ')'){
                pre = i - dp[i - 1] - 1;  // p 位置
                if(pre >= 0 && str[pre] == '(') {
                    // 注意 pre 可能越界，越界则为 0
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

