package com.myCompany.xor;

import java.util.Arrays;

/**
 * 异或运算应用
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class FindOddNumber {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 5, 6, 19, 100, 19, 6, 6, 2, 100, 2, 6, 2};
        int oddOneNumber = findOddOneNumber(nums1);
        System.out.println("oddOneNumber = " + oddOneNumber);
        int[] nums2 = new int[]{2, 5, 6, 19, 100, 19, 6, 6, 2, 2, 6, 2};
        int[] oddTwoNumber = findOddTwoNumber(nums2);
        System.out.println("oddTwoNumber = " + Arrays.toString(oddTwoNumber));
    }

    /**
     * 1、有一些数(nums数组)，只有一个数字出现的次数是奇数次，其他数出现的次数是偶数次
     * 寻找此数
     *
     * @param nums nums
     * @return 出现次数为奇数次的数
     */
    public static int findOddOneNumber(int[] nums) {
        int e = 0;
        for (int num : nums) {
            e ^= num;
        }
        return e;
    }

    /**
     * 2、有一些数(nums数组)，只有两个数字出现的次数是奇数次，其他数出现的次数是偶数次
     * 寻找着两个数字
     *
     * @param nums nums
     * @return 出现次数为奇数次的俩数
     */
    public static int[] findOddTwoNumber(int[] nums) {
        int e = 0;
        // 第一次进行异或运算后，结果为这俩数异或的值，由于不相等，则不为0
        for (int num : nums) {
            e ^= num;
        }
        // 寻找第一个1的位置，1 = 1 ^ 0,
        // 所有数字可以分为两类，一类数字在这个位置都是1，另一类是0，这俩数字就分别位于其中
        // 从右边开始找
        int rightOne = e & (~e + 1);
        // 再次对nums异或，由 num&rightOne == 0，筛选出那些在此位置上是0的数字
        int eAgain = 0;
        for (int num : nums) {
            if ((num & rightOne) == 1) {
                eAgain ^= num;
            }
        }
        // e是他们俩的异或，eAgain是其中一个数字，则另一个数字就是 e^eAgain
        return new int[]{eAgain, e ^ eAgain};
    }
}
