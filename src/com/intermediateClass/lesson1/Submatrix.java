package com.intermediateClass.lesson1;

/**
 * 预处理法（查询的方法很频繁，则先做预处理）
 *
 * 给定一个 N*N 的矩阵matrix，只有 0 和 1 两种值，返回边框全是 1 的最大正方形的边长 长度。
 *
 * 一个 n 阶矩阵 所有子矩阵个数 为 n^4
 * 所有 正方形 矩阵个数： n^3规模
 */
public class Submatrix {

    public static int method1(int[][] matrix) {
        // 两个嵌套for 循环，枚举正方形顶点的位置
        // 再来一个for，枚举正方形的边长
        int N = matrix.length;
        int M = matrix[0].length;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                // 枚举边长
                for (int border = 1; border <= Math.min(N - row, M - col); border++) {
                    // 左上角点(row，col) 边长是 border

                    // 验证这个正方形的边界是不是全是 1
                }
            }
        }
        return 1;
    }

    public static int method2(int[][] m){
        // 生成一个辅助矩阵 right
        // right记录着 每个位置，包含自己 右边有多少个连续的 1， 从左往右，从下往上

        // 再来一个矩阵 down ， 记录 每个位置，包含自己，下方有多少个连续的 1， 从下往上，从左往右
        return 0;
    }
}
