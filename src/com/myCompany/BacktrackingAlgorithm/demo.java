package com.myCompany.BacktrackingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/6/6 - 8:26
 */
public class demo {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k = 3;
        int[] res = kWeakestRows(mat, k);
        System.out.println(Arrays.toString(res));
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || mat.length == 0){
            return null;
        }
        int[][] sumOfRows = new int[mat.length][2];
        for (int i = 0; i < mat.length; i++) {
            sumOfRows[i][0] = sumInRow(mat[i]);
            sumOfRows[i][1] = i;
        }
        for (int[] t : sumOfRows){
            System.out.println(Arrays.toString(t));
        }
        System.out.println("============");
        List<int[]> list = new ArrayList<>(sumOfRows.length);
        list.addAll(Arrays.asList(sumOfRows));
        list.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        for (int[] t : list){
            System.out.println(Arrays.toString(t));
        }
        System.out.println("==============");
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i)[1];
        }
        return res;
    }

    public static int sumInRow(int[] row) {
        int sum = 0;
        for (int t : row) {
            sum += t;
        }
        return sum;
    }
}
