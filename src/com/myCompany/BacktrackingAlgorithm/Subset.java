package com.myCompany.BacktrackingAlgorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/6/4 - 18:01
 */
public class Subset {
    // 求一个数组所有的子集
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println("subsets = " + subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, path, res);
        return res;
    }

    // 按照每层子集有多少个数
    private static void dfs(int[] nums, int begin, Deque<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(nums, i + 1, path, res);
            path.removeLast();
        }
    }
}
