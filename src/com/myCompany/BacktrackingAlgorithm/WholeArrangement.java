package com.myCompany.BacktrackingAlgorithm;

import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/6/4 - 16:21
 */
public class WholeArrangement {
    // 数组的全排列
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            System.out.println(list.toString());
        }
    }

    // 输出一个数组的全排列
    public static List<List<Integer>> permute(int[] nums) {
        // 最后的结果
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        // 数组长度
        int len = nums.length;
        // 标记数字是否被使用过
        boolean[] used = new boolean[len];
        // 存放排列结果的栈
        Deque<Integer> path = new ArrayDeque<>();
        // 深度优先遍历
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    // 深度优先遍历
    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 如果当前的深度等于数组长度，即所有的数字都参与了排列后，把当前排列结果加入到res中
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            // 当当前数字没有被使用（遍历）过
            if (!used[i]) {
                // 添加数字到栈中
                path.addLast(nums[i]);
                // 标记该数字已使用
                used[i] = true;
                // 递归前往下一层
                dfs(nums, len, depth + 1, path, used, res);
                // 回溯，撤销对最后一个数字的标记
                used[i] = false;
                // 删除最后一个数字
                path.removeLast();
            }
        }
    }

}
