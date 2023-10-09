package com.myCompany.hashTable;

import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/7/25 - 11:06
 */
public class RestoresAnArrayFromAdjacentElements {
    // 力扣每日一题：从相邻元素对还原数组
    /*
    *   存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

        给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

        题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

        返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。

        输入：adjacentPairs = [[2,1],[3,4],[3,2]]
        输出：[1,2,3,4]
        解释：数组的所有相邻元素对都在 adjacentPairs 中。
        特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。

     */

    public static void main(String[] args) {
        int[][] adjacentPairs = new int[][]{
                {4,-2},{1,4},{-3,1}
//                {1000,-1000}
        };
        int[] res = restoreArray(adjacentPairs);
        System.out.println("res = " + Arrays.toString(res));
    }

    // 使用哈希表
    private static int[] restoreArray(int[][] adjacentPairs) {
        // 哈希表，key为各个元素，value为该元素与谁相邻
        HashMap<Integer, List<Integer>> adjacentTable = new HashMap<>();
        for (int[] temp : adjacentPairs){
            // 如果已经存在此key，则不put
            adjacentTable.putIfAbsent(temp[0],new ArrayList<>());
            adjacentTable.putIfAbsent(temp[1],new ArrayList<>());
            // 向list中追加相邻的元素
            adjacentTable.get(temp[0]).add(temp[1]);
            adjacentTable.get(temp[1]).add(temp[0]);
        }
        System.out.println(adjacentTable);
        int[] res =new int[adjacentTable.size()];
        // 找到头元素
        for (Map.Entry<Integer,List<Integer>> entry : adjacentTable.entrySet()){
            if (entry.getValue().size() == 1){
                res[0] = (entry.getKey());
                res[1] = entry.getValue().get(0);
                break;
            }
        }
        // 添加第二个元素
        int i = 2;
        while (i != res.length) {
            List<Integer> list = adjacentTable.get(res[i-1]);
            res[i] = (res[i-2] == (list.get(0)) ? list.get(1) : list.get(0));
            i++;
        }
        return res;
    }
}
