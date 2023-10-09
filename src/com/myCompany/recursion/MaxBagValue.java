package com.myCompany.recursion;

/**
 * 给定两个长度为 N 的数组，weights和 values，weights[i]和value[i]分别表示i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多价值是多少？
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class MaxBagValue {
    public static void main(String[] args) {
        int[] weights = new int[]{2, 2, 2};
        int[] values = new int[]{1, 2, 4};
        int bag = 4;
        int maxValue = process(weights, values, 0, 0, 0, bag);
        System.out.println(maxValue);
    }

    // i之前已经选择好了, 返回最大价值
    // alreadyWeight，之前做过的决定，所达到的重量
    private static int process(int[] weights, int[] values, int i, int alreadyWeight, int alreadyValue, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }
        // 来到末尾，没有货物了，价值为0
        if (i == weights.length) {
            return alreadyValue;
        }
        // 要 i 和 不要 i
        return Math.max(
                process(weights, values, i + 1, alreadyWeight, alreadyValue, bag),
                process(weights, values, i + 1,
                        alreadyWeight + weights[i], alreadyValue + values[i], bag)
        );
    }
}
