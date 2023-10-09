package com.myCompany.tenAlgorithm;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/5/24 - 8:12
 */
public class Floyd {

    public static void main(String[] args) {
        // 顶点数组
//        char[] vertex = {'A','B','C','D','E','F','G'};
        String[] vertex = new String[27];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = String.valueOf(i + 1);
        }
        System.out.println("顶点：");
        System.out.println(Arrays.toString(vertex));
        System.out.println();
        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 1, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, 1, N, N};
        matrix[1] = new int[]{1, 0, 1, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[2] = new int[]{N, 1, 0, 1, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, 1, N, N};
        matrix[3] = new int[]{N, N, 1, 0, 1,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, 1, 1, N, N};
        matrix[4] = new int[]{N, N, N, 1, 0,
                1, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, 1, N, N, N};
        matrix[5] = new int[]{N, N, N, N, 1,
                0, 1, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, 1, 1, N, N, N};
        matrix[6] = new int[]{N, N, N, N, N,
                1, 0, 1, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, 1, N, N, N, N, N};
        matrix[7] = new int[]{N, N, N, N, N,
                N, 1, 0, 1, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, 1, N, N, N, N, N};
        matrix[8] = new int[]{N, N, N, N, N,
                N, N, 1, 0, 1,
                N, N, N, N, 1,
                1, 1, N, N, 1,
                1, 1, N, N, N, N, N};
        matrix[9] = new int[]{N, N, N, N, N,
                N, N, N, 1, 0,
                1, N, 1, N, 1,
                N, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[10] = new int[]{N, N, N, N, N,
                N, N, N, N, 1,
                0, 1, 1, N, N,
                N, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[11] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                1, 0, 1, 1, N,
                N, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[12] = new int[]{N, N, N, N, N,
                N, N, N, N, 1,
                1, 1, 0, 1, 1,
                N, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[13] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, 1, 1, 0, 1,
                1, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[14] = new int[]{N, N, N, N, N,
                N, N, N, 1, 1,
                N, N, 1, 1, 0,
                1, N, N, N, N,
                N, N, N, N, N, N, N};
        matrix[15] = new int[]{N, N, N, N, N,
                N, N, N, 1, N,
                N, N, N, N, 1,
                0, 1, 1, N, N,
                N, N, N, N, N, N, N};
        matrix[16] = new int[]{N, N, N, N, N,
                N, N, N, 1, N,
                N, N, N, N, N,
                1, 0, 1, N, N,
                1, N, N, N, N, N, N};
        matrix[17] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                1, 1, 0, 1, 1,
                N, N, N, N, N, N, N};
        matrix[18] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, 1, 0, 1,
                N, N, N, N, N, N, N};
        matrix[19] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, 1, 1, 0,
                1, N, N, N, N, N, N};
        matrix[20] = new int[]{N, N, N, N, N,
                N, N, N, 1, N,
                N, N, N, N, N,
                N, 1, N, N, 1,
                0, 1, 1, N, N, N, 1};
        matrix[21] = new int[]{N, N, N, N, N,
                N, 1, 1, 1, N,
                N, N, N, N, N,
                N, N, N, N, N,
                1, 0, 1, N, N, N, N};
        matrix[22] = new int[]{N, N, N, N, N,
                1, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                1, 1, 0, 1, N, 1, N};
        matrix[23] = new int[]{N, N, N, 1, 1,
                1, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, 1, 0, 1, 1, N};
        matrix[24] = new int[]{1, N, 1, 1, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, 1, 0, 1, N};
        matrix[25] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, 1, 1, 1, 0, 1};
        matrix[26] = new int[]{N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                N, N, N, N, N,
                1, N, N, N, N, 1, 0};

        // Graph对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        // 调用弗洛伊德算法
        graph.floyd();
        // 显示
        graph.show(vertex);

        // 得到1->15, 1->12, 1->27, 15->12, 12->15, 15->27, 12->27的最短路径
        int[][] dis = graph.getDis();

        int[] startsAndEnds = {0,11,14,26};
        for (int i = 0; i < startsAndEnds.length - 1; i++) {
            for (int j = 0; j < startsAndEnds.length; j++) {
                int len = dis[startsAndEnds[i]][startsAndEnds[j]];
                System.out.println((startsAndEnds[i] + 1) + "到" +(startsAndEnds[j]+1) + "的最短路径为：");
                getPath(dis,startsAndEnds[i],startsAndEnds[j],len);
                System.out.print(startsAndEnds[j]+1);
                System.out.println();
                System.out.println();
            }
        }
    }

    /**
     * 得到 start 点到 end 点的最短路径(其中之一)
     * @param dis 邻接矩阵(各个点之间的距离矩阵)
     * @param start 起始点
     * @param end 终止点
     * @param len 两点之间的最短距离
     */
    public static void getPath(int[][] dis, int start,int end,int len){
        for (int i = 0; i < dis[end].length; i++) {
            if (dis[end][i] == 1) {
                if (dis[start][i] == len - 1) {
                    getPath(dis,start,i,len-1);
                    System.out.print(i+1 + "->");
                    break;
                }
            }
        }
    }
}


// 创建图
class Graph {
    // 存放顶点数组
    private String[] vertex;
    // 保存从各个顶点出发到其他顶点的距离，最后的结果，也是保存在该数组
    private int[][] dis;
    // 保存到达目标顶点的前驱顶点
    private int[][] pre;

    /**
     * 构造器
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, String[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show(String[] vertex) {
        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组一行数据输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + "  ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是" + dis[k][i] + ")  ");
            }
            System.out.println();
            System.out.println();
        }
    }

    // 弗洛伊德算法
    public void floyd() {
        // len保存距离
        int len = 0;
        // 对中间顶点的遍历， k 就是中间顶点的下标  'A','B','C','D','E','F','G'
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点开始出发'A','B','C','D','E','F','G'
            for (int i = 0; i < dis.length; i++) {
                // j是终点节点 'A','B','C','D','E','F','G'
                for (int j = 0; j < dis.length; j++) {
                    // 求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    len = dis[i][k] + dis[k][j];
                    // 若len小于dis[i][j]
                    if (len < dis[i][j]) {
                        // 更新距离
                        dis[i][j] = len;
                        // 更新前驱结点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

    public int[][] getDis() {
        return dis;
    }
}
