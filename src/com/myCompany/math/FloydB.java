package com.myCompany.math;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/6/10 - 14:05
 */
public class FloydB {
    public static void main(String[] args) {
        // 顶点数组
        String[] vertex = new String[64];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = String.valueOf(i + 1);
        }
        System.out.println("顶点：");
        System.out.println(Arrays.toString(vertex));
        System.out.println();
        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[64];
            for (int j = 0; j < matrix.length; j++) {
                if (j == i) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = N;
                }
            }
            matrix[i][i] = 0;
        }
        matrix[0][1] = 1;
        matrix[0][8] = 1;
        int a = 0, b = 2, c = 8, d = 9;
        for (int i = 1; i < 8; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            a++;
            b++;
            c++;
            d++;
        }
        matrix[7][8] = N;
        a = 0;
        b = 1;
        c = 7;
        d = 9;
        int e = 16;
        int f = 17;
        for (int i = 8; i < 16; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[8][7] = N;
        matrix[15][8] = N;
        matrix[15][16] = N;
        matrix[15][24] = N;
        a = 7;
        b = 8;
        c = 15;
        d = 17;
        e = 23;
        f = 24;
        for (int i = 16; i < 24; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[16][7] = N;
        matrix[16][15] = N;
        matrix[16][23] = N;
        matrix[23][24] = N;
        a = 16;
        b = 17;
        c = 23;
        d = 25;
        e = 32;
        f = 33;
        for (int i = 24; i < 32; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[24][23] = N;
        matrix[31][24] = N;
        matrix[31][32] = N;
        matrix[31][40] = N;
        a = 23;
        b = 24;
        c = 31;
        d = 33;
        e = 39;
        f = 40;
        for (int i = 32; i < 40; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[32][23] = N;
        matrix[32][31] = N;
        matrix[32][39] = N;
        matrix[39][40] = N;
        a = 32;
        b = 33;
        c = 39;
        d = 41;
        e = 48;
        f = 49;
        for (int i = 40; i < 48; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[40][39] = N;
        matrix[47][40] = N;
        matrix[47][48] = N;
        matrix[47][56] = N;
        a = 39;
        b = 40;
        c = 47;
        d = 49;
        e = 55;
        f = 56;
        for (int i = 48; i < 56; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            matrix[i][c] = 1;
            matrix[i][d] = 1;
            matrix[i][e] = 1;
            matrix[i][f] = 1;
            a++;
            b++;
            c++;
            d++;
            e++;
            f++;
        }
        matrix[48][39] = N;
        matrix[48][47] = N;
        matrix[48][55] = N;
        matrix[55][56] = N;
        a = 48;
        b = 49;
        for (int i = 56; i < 64; i++) {
            matrix[i][a] = 1;
            matrix[i][b] = 1;
            a++;
            b++;
        }
        matrix[63][56] = N;


        // Graph对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        // 调用弗洛伊德算法
        graph.floyd();
        // 显示
        graph.show(vertex);

        // 得到1->15, 1->12, 1->27, 15->12, 12->15, 15->27, 12->27的最短路径
        int[][] dis = graph.getDis();

        int[] startsAndEnds = {0,29,38,54,61,63};
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
