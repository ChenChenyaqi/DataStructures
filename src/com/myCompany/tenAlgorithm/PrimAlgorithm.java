package com.myCompany.tenAlgorithm;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/7/22 - 8:36
 */
public class PrimAlgorithm {
    // 此数表示无法通行
    public static final int N = 10000;

    // prim算法解决村庄修路问题
    public static void main(String[] args) {
        // 创建村庄
        char[] villages = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 创建村庄之间的距离邻接矩阵
        int[][] weight = new int[][]{
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}};
        // 创建MyGraph对象
        MyGraph graph = new MyGraph(villages.length);
        // 创建MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, villages.length, villages, weight);
        // 输出
        minTree.showGraph(graph);
        // 调用prim算法
        // 以'A'为出发顶点
        minTree.prim(graph, 0);
        System.out.println("####################");
        // 以'E'为出发顶点
        minTree.prim(graph, 4);
    }


    /**
     * 最小生成树
     */
    private static class MinTree {

        /**
         * 创建图的邻接矩阵
         *
         * @param graph       图
         * @param numOfVertex 顶点数
         * @param date        顶点的值
         * @param weight      邻接矩阵
         */
        public void createGraph(MyGraph graph, int numOfVertex, char[] date, int[][] weight) {
            for (int i = 0; i < numOfVertex; i++) {
                graph.data[i] = date[i];
                for (int j = 0; j < numOfVertex; j++) {
                    graph.weight[i][j] = weight[i][j];
                }
            }
        }

        /**
         * 显示图的邻接矩阵
         *
         * @param graph 图
         */
        public void showGraph(MyGraph graph) {
            for (int[] link : graph.weight) {
                System.out.println(Arrays.toString(link));
            }
        }

        /**
         * prim算法，得到最小生成树
         *
         * @param graph 图
         * @param v     表示从第几个顶点开始生成（从0开始）
         */
        public void prim(MyGraph graph, int v) {
            // visited[]表示顶点是否被访问过, 默认都是false
            boolean[] visited = new boolean[graph.numOfVertex];
            // 把当前的顶点标记为已访问
            visited[v] = true;
            // h1，h2记录俩顶点
            int h1 = -1;
            int h2 = -1;
            int minWeight = N;
            // graph有numOfVertex个顶点，则最小生成树对应有numOfVertex - 1 条边
            for (int k = 1; k < graph.numOfVertex; k++) {
                // 寻找从i出发的最小距离的j
                for (int i = 0; i < graph.numOfVertex; i++) {
                    for (int j = 0; j < graph.numOfVertex; j++) {
                        // i被访问且j没被访问且两个的权值小于minWeight
                        if (visited[i] && !visited[j] && graph.weight[i][j] < minWeight) {
                            // 更新minWeight值
                            minWeight = graph.weight[i][j];
                            // 记录此时的俩顶点
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
                // 输出找到的一条最小的边
                System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
                // 标记h2已被访问
                visited[h2] = true;
                // 重新设置minWeight的值
                minWeight = N;
            }
        }
    }

    /**
     * 图
     */
    private static class MyGraph {
        // 图的顶点数
        int numOfVertex;
        // 存放顶点的值
        char[] data;
        // 顶点之间的权重，邻接矩阵
        int[][] weight;

        public MyGraph(int numOfVertex) {
            this.numOfVertex = numOfVertex;
            data = new char[numOfVertex];
            weight = new int[numOfVertex][numOfVertex];
        }
    }
}
