package com.newDataStructures.violenceRecursive;

/**
 * N 皇后问题
 */
public class Nqueen {

    public static void main(String[] args) {
        System.out.println(nQueen(14));
    }

    public static int nQueen(int n) {
        if (n == 0) {
            return 0;
        }
        // 代表 记录过的 皇后 所在的列坐标
        int[] record = new int[n];
        return process(record, 0, n);
    }

    private static int process(int[] record, int i, int n) {
        // 如果 当前行 i 来到了最后一行的下一行，说明找到了一个排列
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            // 当前行，尝试遍所有列，看看 会不会与 之前的皇后冲突
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(record, i + 1, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        // 只看 当前 i 行之前的
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    // 常数优化
    // 请不要超过32皇后问题
    // ...
}
