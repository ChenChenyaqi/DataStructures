package com.myCompany.recursion;

/**
 * @author chenyaqi
 * @date 2021/5/4 - 16:37
 */
public class EightQueens {
    // 定义一个max表示共有多少个皇后
    private int max = 8;
    // 定义一个数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    // 总共的解法
    int count = 0;

    public static void main(String[] args) {
        // 测试
        EightQueens eightQueens = new EightQueens();
        eightQueens.placeQueens(0);
        System.out.println("总共有: " + eightQueens.count + "种解法");
    }

    // 放置第n个皇后
    private void placeQueens(int n){
        // 如果要放置的皇后是max个，即第9个皇后，说明前面8个已经放完
        if (n == max){
            printQueens();
            return;

        }
        // 依次放入皇后
        for (int i = 0; i < max; i++) {
            // 先把当前的皇后放到第一列
            array[n] = i;
            if (!isConflict(n)){
                // 不冲突则放置下一个
                placeQueens(n+1);
            }
            // 冲突则把当前皇后放置下一列
        }
    }

    // 判断放置的第n个皇后是否跟前面的有冲突
    private boolean isConflict(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return true;
            }
        }
        return false;
    }

    // 打印皇后摆放的位置
    private void printQueens(){
        count++;
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
