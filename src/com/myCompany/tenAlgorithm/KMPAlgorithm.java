package com.myCompany.tenAlgorithm;

        import java.util.ArrayList;
        import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/7/20 - 7:15
 */
public class KMPAlgorithm {
    // KMP字符串匹配算法
    // KMP算法是一种字符串匹配算法，求pattern模式串在text主串中出现的位置。
    public static void main(String[] args) {
        // 算例1
        String str1 = "BBCABCDABABCDABCDABDE";
        String str2 = "ABCDABD";
        List<Integer> res = search(str1, str2); // 13
        System.out.println(res);
        // 算例2
        str1 = "abababzaababababababa";
        str2 = "abababa";
        System.out.println(search(str1, str2)); // 8  10  12  14
    }

    /**
     * 获得pattern的最大匹配数表
     * 规则为：用pattern子串的所有真前缀匹配其所有真后缀，求出最大匹配长度
     * 例如：ababa
     * 真前缀： a  ab  aba   abab
     * 真后缀： a  ba  aba   baba
     * 最大匹配长度为 3 , maxMatchLengths["ababa".length() - 1] = 3;
     *
     * @param pattern 要匹配的字符串
     * @return pattern各个子串最大匹配数所组成的maxMatchLengths数组
     */
    public static int[] calculateMaxMatchLengths(String pattern) {
        // 最大匹配数表
        int[] maxMatchLengths = new int[pattern.length()];
        // 先令最大长度为0，也即pattern[0]的最大匹配数为0
        int maxLength = 0;
        // i 指针从1开始
        for (int i = 1; i < pattern.length(); i++) {
            // 当maxLength指针与i指针指向的值不同时，后退(左移)maxLength指针，直至相等或maxLength为0
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1];
            }
            // 如果相等
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                // 最大长度增加
                maxLength++;
            }
            // 记录子串i的最大匹配数
            maxMatchLengths[i] = maxLength;
        }

        return maxMatchLengths;
    }

    /**
     * 在text中寻找pattern，返回所有匹配位置开头
     * 根据最大匹配表，每当不匹配时，调整count指针的位置
     * 规则为：count = maxMatchLengths[count - 1];
     *
     * @param text    主串
     * @param pattern 模式串
     * @return 返回模式串在主串中出现的各个位置的开头
     */
    public static List<Integer> search(String text, String pattern) {
        // 各个成功匹配的位置开头
        List<Integer> positions = new ArrayList<>();
        // 得到pattern的最大匹配数表
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        // count指针
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            // 当count指针和i指针指向的值不同时，根据规则左移count指针，直至相同或count为0
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            // 若匹配成功
            if (pattern.charAt(count) == text.charAt(i)) {
                // count指针右移
                count++;
            }
            // 当count等于模式串长度，即为成功匹配完
            if (count == pattern.length()) {
                // 向positions中添加位置
                positions.add(i - pattern.length() + 1);
                // 左移count继续匹配
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }
}
