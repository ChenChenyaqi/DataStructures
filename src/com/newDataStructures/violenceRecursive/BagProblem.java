package com.newDataStructures.violenceRecursive;

/**
 * 背包问题
 * 给定两个长度都为 N 的数组 weights，values
 * weights[i] 和 values[i] 分别代表 i 号货物的重量和价值。
 * 给定一个正数 bag， 表示一个载重bag的袋子，你装的物品不能超过这个重量
 * 求最多能转下多少价值?
 * <p>
 * 依旧从左到右试
 */
public class BagProblem {

    // i... 的 货物自由选择，形成的最大价值返回
    // 重量永远不超过bag
    // 之前做的决定，所形成的的重量 alreadyWeight
    public static int process(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }

        if (i == weights.length) {
            return 0;
        }

        return Math.max(
                // 不要 i 号
                process(weights, values, i + 1, alreadyWeight, bag),
                // 要 i 号
                values[i] + process(weights, values, i + 1, alreadyWeight + weights[i], bag)
        );
    }

    // 考虑之前的
    public static int process2(int[] weights, int[] values, int i, int alreadyWeight, int alreadyValue, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }
        if (i == values.length) {
            return alreadyValue;
        }

        return Math.max(
                process2(weights, values, i + 1, alreadyWeight, alreadyValue, bag),
                process2(weights, values, i + 1, alreadyWeight + weights[i], alreadyValue + values[i], bag)
        );
    }
}