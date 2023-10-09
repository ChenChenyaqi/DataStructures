package com.myCompany.recursion;

/**
 * N皇后问题
 * @author Chen Yaqi
 * @version 1.0
 */
public class NQueens {
    public static void main(String[] args) {
        // 几皇后
        int n = 10;
        // record[i] 记录 第 i + 1 个皇后放在第 record[i] 列上
        int[] record = new int[n];
        // 0：当前在第0行
        // record：前面的放置记录
        // n：总共有n行
        int count = process(0, record, n);
        System.out.println("count = " + count);
    }

    // 主程序
    private static int process(int i, int[] record, int n) {
        if (i == n){
            // 到最后一行的下一行了，即安排完了所有皇后
            return 1;
        }
        // 方案个数
        int res = 0;
        for (int j = 0; j < n; j++) {
            // 如果把当前行的皇后放置在j列，是否会跟前面的冲突
            if (isValid(i,j,record)){
                record[i] = j;
                res += process(i+1,record,n);
            }
        }
        return res;
    }

    // 判断第i行放置在第j列的皇后跟前面的是否冲突
    private static boolean isValid(int i, int j, int[] record) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)){
                return false;
            }
        }
        return true;
    }
}
