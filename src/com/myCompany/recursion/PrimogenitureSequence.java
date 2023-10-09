package com.myCompany.recursion;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * @author chenyaqi
 * @date 2021/6/3 - 14:11
 */
public class PrimogenitureSequence {
    // 使用哈希表存放之前记录过的节点
    private static Hashtable<Integer,Integer> hashtable = new Hashtable<>();
    private static int count = 0;

    // 寻找一个数组中，最长的递增子序列长度
    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 4, 3};
        int length = getMaxLenOfNums(nums);
        // 方法一：
        // 递归+哈希表
        System.out.println("length = " + length);
        // 方法二：
        // 动态规划
        System.out.println(dynamicPro(nums));
    }

    // 得到数组nums的最长子序列长度
    private static int getMaxLenOfNums(int[] nums) {
        int length = 1;
        // 遍历数组，依次得到从i位置开始的子序列长度
        for (int i = 0; i < nums.length; i++) {
            length = Math.max(getLen(nums,i), length);
        }
        return length;
    }

    // 得到以某个节点开始的最长子序列长度
    private static int getLen(int[] nums, int i) {
        // 如果i已经是最后一个元素，则返回1
        if (i == nums.length - 1){
            return 1;
        }
        // 如果此节点之前记录过，则直接返回值
        if (hashtable.containsKey(i)){
            count++;
            return hashtable.get(i);
        }
        int len = 1;
        // 从i的下一个位置开始，以此位置为新的节点，求其最大子序列长度
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]){
                len = Math.max(getLen(nums,j) + 1,len);
            }
        }
        hashtable.put(i,len);
        return len;
    }

    // 动态规划求解
    public static int dynamicPro(int[] nums){
        // 初始化，表示前几个数最大长度
        int[] len = new int[nums.length];
        int maxLen = 1;
        Arrays.fill(len,1);
        // 前i个数
        for (int i = 0; i < nums.length; i++) {
            // 依次比较
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    len[i] = Math.max(len[i], len[j] + 1);
                }
                maxLen = Math.max(maxLen, len[i]);
            }
        }
        return maxLen;
    }
}
