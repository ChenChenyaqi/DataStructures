package com.intermediateClass.lesson3;

/**
 * 宏观调度
 *
 * 螺旋打印矩阵，从左到右，从上到下，从右到左，从下到上
 *
 * 思路：先实现给定一个左上角和右下角，循环打印这个边框
 * 然后左上角下移，右下角上移
 */
public class HelicalPrintMatrix {

    public static void spiralOrderPrint(int[][] m){
        // 左上角点
        int tR = 0;
        int tC = 0;
        // 右下角点
        int dR = m.length - 1;
        int dC = m[0].length - 1;
        while(tR <= dR && tC <= dC){
            printBorder(m, tR++, tC++, dR--, dC--);
        }

    }

    // 左上角：a 行， b 列
    // 右下角：c 行， d 列
    public static void printBorder(int[][] m, int a, int b, int c, int d){
        // 同一行
        if(a == c){
            for (int i = b; i <= d; i++) {
                System.out.print(m[a][i] + " ");
            }
        } else if(b == d){ // 同一列
            for (int i = a; i <= c; i++) {
                System.out.println(m[i][b] + " ");
            }
        } else {
            // 打印边框
            int curC = b;
            int curR = a;
            while(curC != d){
                System.out.println(m[a][curC] + " ");
                curC++;
            }
            while(curR != c){
                System.out.println(m[curR][d] + " ");
                curR++;
            }
            while(curC != b){
                System.out.println(m[c][curC] + " ");
                curC--;
            }
            while(curR != a){
                System.out.println(m[curR][b] +" ");
                curR--;
            }
        }
    }
}
