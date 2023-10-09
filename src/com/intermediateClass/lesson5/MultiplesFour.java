package com.intermediateClass.lesson5;

/**
 * 给定一个数组arr， 如果通过调整可以做到arr中任意两个相邻的数字相乘是 4 的倍数
 * 返回 true，否则返回 false
 *
 * 思路：
 * 统计三种数的个数
 * 奇数  a 个
 * 偶数：只有一个2因子的数  b 个
 *       包含4因子的数    c 个
 *  1、b == 0  摆放格式："奇4奇4奇4奇"  a == 1时：c >= 1, a > 1时：c >= a - 1
 *  2、b != 0 摆放格式："2222222 4奇4奇4奇" a == 0时：c >= 0, a == 1时: c >= 1, a > 1: c >= a
 *              b == 1 时，最少需要 c >= 1
 */
public class MultiplesFour {

}
