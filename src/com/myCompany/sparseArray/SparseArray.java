package com.myCompany.sparseArray;

import java.io.*;

public class SparseArray {

    public static void main(String[] args) throws IOException {
        // 创建一个原始的二维数组11*11
        // 0:表示没有棋子，1：表示黑子，2：表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[7][6] = 2;
        System.out.println("原始数组：");
        for (int[] row : chessArr1) {
            for (int item : row) {
                System.out.printf("%3d", item);
            }
            System.out.println();
        }
        // 将二维数组转化为稀疏数组
        // 1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; // count用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 存储到bat文件里
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("sparseArr.bat"));

        System.out.println("变换后的稀疏数组：");
        for (int[] row : sparseArr) {
            for (int item : row) {
                System.out.printf("%3d", item);
                dos.write(item);
            }
            System.out.println();
        }

        DataInputStream dis = new DataInputStream(new FileInputStream("sparseArr.bat"));
        byte[] val = new byte[3];
        int len = 0;
        while ((len = dis.read(val)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.printf("%3d",val[i]);
            }
            System.out.println();
        }

        // 3.恢复稀疏数组为二维数组
        int[][] chessArray2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArray2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复后的原始数组：");
        for (int[] row : chessArray2) {
            for (int item : row) {
                System.out.printf("%3d", item);
            }
            System.out.println();
        }
    }
}
