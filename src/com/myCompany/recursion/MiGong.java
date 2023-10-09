package com.myCompany.recursion;

/**
 * @author chenyaqi
 * @date 2021/5/4 - 13:59
 */
public class MiGong {
    public static void main(String[] args) {

        // 初始化迷宫地图
        int[][] map = new int[8][7];

        for (int i = 0; i < 8; i++) {
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        // 显示迷宫
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(setWay(map, 1, 1));

        // 显示迷宫
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 走迷宫
     *
     * @param map 迷宫数组
     * @param i   初始位置
     * @param j   初始位置
     * @return 是否走通
     */
    private static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 到达终点
            return true;
        } else {
            // 若这个点还没有走过
            if (map[i][j] == 0) {
                // 策略，向下 向右 向上 向左 走
                // 假定此点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // map[i][j] = 1, 2, 3的情况
                return false;
            }
        }

    }

}
