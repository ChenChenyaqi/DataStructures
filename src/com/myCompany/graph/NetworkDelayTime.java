package com.myCompany.graph;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/8/2 - 9:55
 */
public class NetworkDelayTime {
    // 网络延迟时间
    /*
     * 有 n 个网络节点，标记为 1 到 n。
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 
     * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     */
    public static void main(String[] args) {
        int[][] times = new int[][]{
                {1, 2, 1}};
        int n = 2;
        int k = 2;
        int res = networkDelayTime(times, n, k);
        System.out.println("res = " + res);
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        int[][] path = new int[n][n];
        for (int[] t : path){
            Arrays.fill(t,999);
        }
        for (int[] time : times) {
            path[time[0] - 1][time[1] - 1] = time[2];
            path[time[0] -1][time[0] - 1] = 0;
            path[time[1] - 1][time[1] - 1] = 0;
        }
        for(int[] t : path){
            System.out.println("t = " + Arrays.toString(t));
    }

        int[][] pre = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(pre[i], i);
        }
        for(int[] t : pre){
            System.out.println("p = " + Arrays.toString(t));
        }

        // len保存距离
        int len = 0;
        // 对中间顶点的遍历， k 就是中间顶点的下标  'A','B','C','D','E','F','G'
        for (int h = 0; h < path.length; h++) {
            // 从i顶点开始出发'A','B','C','D','E','F','G'
            for (int i = 0; i < path.length; i++) {
                // j是终点节点 'A','B','C','D','E','F','G'
                for (int j = 0; j < path.length; j++) {
                    // 求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    len = path[i][h] + path[h][j];
                    // 若len小于path[i][j]
                    if (len < path[i][j]) {
                        // 更新距离
                        path[i][j] = len;
                        // 更新前驱结点
                        pre[i][j] = pre[h][j];
                    }
                }
            }
        }
        for(int[] t : path){
            System.out.println("t = " + Arrays.toString(t));
        }
        for(int[] t : pre){
            System.out.println("p = " + Arrays.toString(t));
        }
        Arrays.sort(path[k-1]);
        if (path[k-1][n-1] == 999){
            return -1;
        } else {
            return path[k - 1][n - 1];
        }
    }
}
