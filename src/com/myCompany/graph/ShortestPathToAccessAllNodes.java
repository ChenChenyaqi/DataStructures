package com.myCompany.graph;

import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/8/7 - 19:29
 */
public class ShortestPathToAccessAllNodes {
    // 访问所有节点的最短路径
    /*
    * [[6,8],[2,9],[1,3,5],[2,6],[5],[2,6,4],[5,3,0,7],[6],[0],[1]]
    * [[1],[0,2,4],[1,3,4],[2],[1,2]]*/
    public static void main(String[] args) {
        Date date1 = new Date();
        System.out.println(date1);
        int[][] graph = new int[][]{
                {1},{0,2,4},{1,3,4},{2},{1,2}
        };
        int res = shortestPathLength(graph);
        System.out.println("res = " + res);
        Date date = new Date();
        System.out.println(date);
    }

    public static int shortestPathLength(int[][] graph) {
        graph = floyd(graph);
        System.out.println(new Date());
        int length = graph.length;

        // 所有节点
        int[] nodes = new int[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = i;
        }
        // 节点被使用过情况
        boolean[] used = new boolean[length];
        // 存放排列结果的栈
        Deque<Integer> path = new ArrayDeque<>();
        // 最后的结果
        List<List<Integer>> res = new ArrayList<>();
        // 深度优先遍历
        dfs(nodes, length, 0, path, used, res);

//        for (List<Integer> t : res){
//            System.out.println("t : " + t);
//        }

        int minPath = Integer.MAX_VALUE;
        for (List<Integer> row : res){
            int temp = 0;
            for (int i = 0; i < length - 1; i++) {
                temp += graph[row.get(i)][row.get(i+1)];
            }
            minPath = Math.min(temp, minPath);
        }
        return minPath;
    }

    private static void dfs(int[] nodes, int length, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 如果当前的深度等于数组长度，即所有的数字都参与了排列后，把当前排列结果加入到res中
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            // 当当前数字没有被使用（遍历）过
            if (!used[i]) {
                // 添加数字到栈中
                path.addLast(nodes[i]);
                // 标记该数字已使用
                used[i] = true;
                // 递归前往下一层
                dfs(nodes, length, depth + 1, path, used, res);
                // 回溯，撤销对最后一个数字的标记
                used[i] = false;
                // 删除最后一个数字
                path.removeLast();
            }
        }
    }

    // 弗洛伊德算法，返回一个各个节点最短路径表
    public static int[][] floyd(int[][] graph) {
        // 创建邻接矩阵
        int length = graph.length;
        int[][] matrix = new int[length][length];
        for (int[] temp : matrix){
            Arrays.fill(temp,999);
        }
        for (int i = 0; i < length; i++) {
            matrix[i][i] = 0;
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i].length == 1 && graph[i][j] == 0){
                    break;
                }
                matrix[i][graph[i][j]] = 1;
                matrix[graph[i][j]][i] = 1;
            }
        }

        // 弗洛伊德算法
        // 前驱节点矩阵
        int[][] pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
        // len保存距离
        int len = 0;
        // 对中间顶点的遍历， k 就是中间顶点的下标  'A','B','C','D','E','F','G'
        for (int k = 0; k < matrix.length; k++) {
            // 从i顶点开始出发'A','B','C','D','E','F','G'
            for (int i = 0; i < matrix.length; i++) {
                // j是终点节点 'A','B','C','D','E','F','G'
                for (int j = 0; j < matrix.length; j++) {
                    // 求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    len = matrix[i][k] + matrix[k][j];
                    // 若len小于matrix[i][j]
                    if (len < matrix[i][j]) {
                        // 更新距离
                        matrix[i][j] = len;
                        // 更新前驱结点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
        for (int[] t : pre){
            System.out.println(  Arrays.toString(t));
        }
        return matrix;
    }
}
