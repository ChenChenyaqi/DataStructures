package com.intermediateClass.lesson4;

/**
 * 如果一个字符串为str，把字符串str前面任意的部分挪到后面形成的字符串叫作str的旋转词
 * 比如 str = "12345", str的旋转词有："23451" "34512" ...
 * 给你俩字符串a，b，请判断 a 和 b 是否互为旋转词
 * 比如 a = "cdab" b = "abcd"  true
 *
 * 解法：需要用 KMP 算法
 */
public class RotationString {

    public static void main(String[] args) {
        String a = "cdab";
        String b = "abcd";
        System.out.println(test(a, b));
    }

    public static boolean test(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        // a和a自己拼起来
        String aa = a + a;
        // 看看b 是不是 aa 这个字符串的字串
        return kmp(aa, b);
    }

    private static boolean kmp(String aa, String b) {
        char[] str1 = aa.toCharArray();
        char[] str2 = b.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNext(str2);
        while(i1 < str1.length && i2 < str2.length){
            if(str1[i1] == str2[i2]){
                i1++;
                i2++;
            } else if(i2 > 0){
                i2 = next[i2];
            } else {
                i1++;
            }
        }
        return i2 == str2.length;
    }

    private static int[] getNext(char[] str2) {
        if(str2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i < str2.length){
            if(str2[i - 1] == str2[cn]){
                cn++;
                next[i] = cn;
                i++;
            } else if(cn > 0){
                cn = next[cn];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }
}
