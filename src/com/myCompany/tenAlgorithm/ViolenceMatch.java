package com.myCompany.tenAlgorithm;

/**
 * @author chenyaqi
 * @date 2021/5/30 - 16:30
 */
public class ViolenceMatch {
    // 暴力匹配
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "好";
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);
        System.out.println(str1.charAt(index));
    }

    public static int violenceMatch(String str1, String str2) {
        // 判断str2是否为空
        if (str2.equals("")){
            return -1;
        }
        // str1、str2转为字符数组
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // str1、str2长度
        int str1Length = chars1.length;
        int str2Length = chars2.length;
        // i、j索引
        int i = 0;
        int j = 0;
        // 索引不越界
        while (i < str1Length && j < str2Length) {
            // 如果相等
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                // i回溯到前一位置
                i = i - j + 1;
                j = 0;
            }
        }
        // 判断是否匹配到
        if (j == str2Length) {
            return i - j;
        }
        return -1;
    }
}
