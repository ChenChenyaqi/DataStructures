package com.my.java.commonlyusedclass.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class StringTest {
    public static void main(String[] args) {
        // String 字面量定义方式
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2); // true
        s2 = "abcd";
        System.out.println(s1 == s2); // false
        System.out.println("s1 = " + s1); // abc
        System.out.println("s2 = " + s2);  // abcd

        String s3 = "123";
        s3 += "4";  // 说明并不是直接在char[]后面扩增
        System.out.println(s3); // 1234

        String s4 = "abcd";
        String s5 = s4.replace("a", "z");
        System.out.println("s4 = " + s4);  // abcd
        System.out.println("s5 = " + s5);  // zbcd   // 说明并不是在final char[]中直接改
        System.out.println(s4 == s5);  // false

        // String new 的方式
        String s6 = new String("abc");
        String s7 = new String("abc");
        System.out.println(s6 == s7);  // false
        System.out.println(s6.equals(s7));  // true
        System.out.println(s1 == s6); // false
        System.out.println(s1 == s7); // false

        String s8 = s6;
        s8 += "d";
        System.out.println("s6 = " + s6);  // abc
        System.out.println("s8 = " + s8);  // abcd
    }

    @Test
    public void test1(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        // 都是字面量参与的是在常量池中进行，有变量名参与的会在堆空间中进行
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);  // true
        System.out.println(s3 == s5);  // false
        System.out.println(s3 == s6);  // false
        System.out.println(s5 == s6);  // false
        System.out.println(s3 == s7);  // false
        System.out.println(s5 == s7);  // false

        String s8 = s5.intern();  // 表示把s5的值放进常量池中
        System.out.println(s3 == s8); // true

        String str = "123abc我的天";
        byte[] utf8 = str.getBytes();
        System.out.println("utf8 = " + Arrays.toString(utf8));
        String str2 = new String(utf8);
        System.out.println(str2);

        StringBuffer sb1 = new StringBuffer("abc");
        sb1.insert(0,"d");
        System.out.println(sb1);
    }
}
