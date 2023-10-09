package com.intermediateClass.lesson1;

/**
 * 有一个函数可以等概率返回 1 ~ 5
 * 设计一个函数可以等概率返回 1 ~ 7
 */
public class RandomFun {

    public static int f(){
        return (int)(Math.random() * 5) + 1;
    }

    // 等概率返回 0 和 1
    public static int r01(){
        int res = 0;
        do{
            res = f();
        } while(res == 3);
        return res < 3 ? 0 : 1;
    }

    // 等概率返回 1 ~ 7
    public static int g(){
        int res = 0;
        do{
            res = (r01() << 2) + (r01() << 1) + r01();
        } while (res == 7);
        return res + 1;
    }

    // 提供f 返回 13 ~ 21， 做出 30 ~ 59
    // 先把f 加工成 0 和 1 发生器
    // 然后 做出 0 ~ 29， 需要 5 个 二进制位，如果拼出的数字 大于 29，重做

    // 以 p 概率返回0， 以 1 - p 概率返回1，怎么加工出 等概率返回 0 和 1的函数
    // 扔两回f，得到 00 11 重做，得到 01 10 分别为 0 和 1
}
