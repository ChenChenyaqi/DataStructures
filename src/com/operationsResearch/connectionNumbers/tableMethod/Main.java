package com.operationsResearch.connectionNumbers.tableMethod;

import java.util.*;

public class Main {

    /*
    * / 表
        double[][] matrix = {
                {10, 20, 5, 9, 10},
                {2, 10, 8, 30, 6},
                {1, 20, 7, 10, 4},
                {8, 6, 3, 7, 5}
        };
        // 产量
        double[] production = {5, 6, 2, 9};
        // 销量
        double[] sales = {4, 4, 6, 4, 4};
        *
        *  // 表
        double[][] matrix = {
                {3, 11, 3, 10},
                {1, 9, 2, 8},
                {7, 4, 10, 5},

        };
        // 产量
        double[] production = {7, 4, 9};
        // 销量
        double[] sales = {3, 6, 5, 6};
    * */


    // 产销平衡 运输问题
    public static void main(String[] args) {
        // 表
        double[][] matrix = {
                {3, 11, 3, 10},
                {1, 9, 2, 8},
                {7, 4, 10, 5},

        };
        // 产量
        double[] production = {7, 4, 9};
        // 销量
        double[] sales = {3, 6, 5, 6};
        process(matrix, production, sales);
    }

    public static void process(double[][] matrix, double[] production, double[] sales) {
        // 最小元素法生成的表
        TableDate[][] table = new TableDate[matrix.length][matrix[0].length];
        // 初始化 table
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = new TableDate(matrix[i][j], i, j);
            }
        }
        // 删除的行号
        Set<Integer> deletedRow = new HashSet<>();
        // 删除的列号
        Set<Integer> deletedCol = new HashSet<>();
        // 已经填入table表中的数字个数
        int insertNumCount = 0;
        // 退出条件
        int outCount = matrix.length + matrix[0].length - 1;
        while (insertNumCount != outCount) {
            // 在没有被删除的行列里找到一个最小元素
            int[] minIndex = findMinElementUnDeleted(matrix, deletedRow, deletedCol);
            // 根据找到的最小元素，更新产量和销量，更新删除的行号列号集合，最小元素添加进table里
            updateTable(matrix, production, sales, table, minIndex, deletedRow, deletedCol, insertNumCount, outCount);
            insertNumCount++;
        }
        System.out.println("利用最小元素法求得结果：");
        printTable(table);

        // 退出循环条件，当所有检验数都 >= 0，则为 false 退出
        boolean flag = false;
        int loopCount = 1;
        while (!flag) {
            flag = true;
            // 位势法求检验数
            // 行、列位势
            Potential[] rowPotential = new Potential[table.length];
            Potential[] colPotential = new Potential[table[0].length];
            for (int i = 0; i < rowPotential.length; i++) {
                rowPotential[i] = new Potential(i, 0);
            }
            for (int i = 0; i < colPotential.length; i++) {
                colPotential[i] = new Potential(i, 1);
            }
            // 队列
            Queue<Potential> queue = new LinkedList<>();
            rowPotential[0].isComputed = true;
            queue.add(rowPotential[0]);
            while (!queue.isEmpty()) {
                Potential p = queue.poll();
                // 如果是行位势
                if (p.rowOrCol == 0) {
                    for (int i = 0; i < table[0].length; i++) {
                        if ((table[p.index][i].count != 0 && !colPotential[i].isComputed) ||
                                (table[p.index][i].count == 0 && table[p.index][i].countZero && !colPotential[i].isComputed)) {
                            colPotential[i].potential = table[p.index][i].cost - p.potential;
                            queue.add(colPotential[i]);
                            colPotential[i].isComputed = true;
                        }
                    }
                } else { // 如果是列位势
                    for (int i = 0; i < table.length; i++) {
                        if ((table[i][p.index].count != 0 && !rowPotential[i].isComputed) ||
                                (table[i][p.index].count == 0 && table[i][p.index].countZero && !rowPotential[i].isComputed)) {
                            rowPotential[i].potential = table[i][p.index].cost - p.potential;
                            queue.add(rowPotential[i]);
                            rowPotential[i].isComputed = true;
                        }
                    }
                }
            }

            System.out.println("计算出的行、列位势：");
            System.out.println(Arrays.toString(rowPotential));
            System.out.println(Arrays.toString(colPotential));

            // 负检验数的行列
            int[] negativeSigmaIndex = new int[2];
            // 求检验数
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    table[i][j].sigma = table[i][j].cost - rowPotential[i].potential - colPotential[j].potential;
                    // 只要有一个检验数 < 0，则flag为false，不退出循环
                    // 一次只找一个
                    if (flag && table[i][j].sigma < 0) {
                        flag = false;
                        negativeSigmaIndex[0] = i;
                        negativeSigmaIndex[1] = j;
                    }
                }
            }
            System.out.println("第 " + loopCount + " 轮：");
            printTable(table);
            // 如果有检验数 < 0
            // 进行闭回路法调整
            if (!flag) {
                // 得到图
                Graph graph = createGraph(table, negativeSigmaIndex[0], negativeSigmaIndex[1]);
                // 依次减少度，得到闭回路
                int[][] pathIndex = getPath(graph, graph.nodes.get(table[negativeSigmaIndex[0]][negativeSigmaIndex[1]]));
                // 出基点坐标
                System.out.print("出基点坐标：");
                System.out.println(Arrays.toString(negativeSigmaIndex));
                // 闭回路
                System.out.println("闭回路：");
                System.out.println(Arrays.deepToString(pathIndex));

                double minCost = Integer.MAX_VALUE;
                for (int j = 0; j < pathIndex.length; j++) {
                    if (j % 2 != 0) {
                        minCost = Math.min(minCost, table[pathIndex[j][0]][pathIndex[j][1]].count);
                    }
                }

                // 根据闭回路出基入基
                boolean hasZeroCount = false;
                for (int j = 0; j < pathIndex.length; j++) {
                    if (j % 2 != 0) {
                        table[pathIndex[j][0]][pathIndex[j][1]].count -= minCost;
                        if (hasZeroCount && table[pathIndex[j][0]][pathIndex[j][1]].count == 0) {
                            table[pathIndex[j][0]][pathIndex[j][1]].countZero = true;
                        }
                        if (!hasZeroCount && table[pathIndex[j][0]][pathIndex[j][1]].count == 0) {
                            hasZeroCount = true;
                        }
                    } else {
                        table[pathIndex[j][0]][pathIndex[j][1]].count += minCost;
                    }
                }
            }
            loopCount++;
            if (loopCount == 10) {
                return;
            }
        }

        double costSum = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].count != 0) {
                    costSum += table[i][j].count * matrix[i][j];
                }
            }
        }

        System.out.println("\n\n===============================");
        System.out.println("总花费：" + costSum);
        System.out.println("运输方案：");
        printTable(table);
        System.out.println("===============================\n\n");

    }

    private static int[][] getPath(Graph graph, Node target) {
        boolean flag = true;
        while (flag) {
            flag = false;
            Iterator<Node> iterator = graph.nodes.values().iterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                if (node.du == 1) {
                    for (Node toNode : node.nexts) {
                        toNode.du--;
                        toNode.edges.remove(node.edges.get(0));
                        toNode.nexts.remove(node);
                    }
                    iterator.remove();
                }
            }
            for (Node node : graph.nodes.values()) {
                if (node.du != 2) {
                    flag = true;
                    break;
                }
            }
        }

        int[][] path = new int[graph.nodes.size()][2];
        int i = 0;
        path[i][0] = target.value.x;
        path[i][1] = target.value.y;

        Set<Node> hashVisited = new HashSet<>();
        hashVisited.add(target);
        getPath(target.nexts.get(0), hashVisited, path, 1);
        return path;
    }

    private static void getPath(Node node, Set<Node> hashVisited, int[][] path, int deep) {
        if (hashVisited.contains(node)) {
            return;
        }
        hashVisited.add(node);
        path[deep][0] = node.value.x;
        path[deep][1] = node.value.y;
        for (Node cur : node.nexts) {
            getPath(cur, hashVisited, path, deep + 1);
        }
    }


    // 创建图
    public static Graph createGraph(TableDate[][] table, int mainI, int mainJ) {
        Graph graph = new Graph();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].count != 0 || table[i][j].countZero || (i == mainI && j == mainJ)) {
                    if (!graph.nodes.containsKey(table[i][j])) {
                        graph.nodes.put(table[i][j], new Node(table[i][j]));
                    }
                    Node fromNode = graph.nodes.get(table[i][j]);
                    // 这一行
                    for (int k = j + 1; k < table[0].length; k++) {
                        if (table[i][k].count != 0 || table[i][k].countZero || (i == mainI && k == mainJ)) {
                            if (!graph.nodes.containsKey(table[i][k])) {
                                graph.nodes.put(table[i][k], new Node(table[i][k]));
                            }
                            Node toNode = graph.nodes.get(table[i][k]);
                            Edge edge = new Edge(fromNode, toNode);
                            graph.edges.add(edge);
                            fromNode.edges.add(edge);
                            toNode.edges.add(edge);
                            fromNode.du++;
                            toNode.du++;
                            fromNode.nexts.add(toNode);
                            toNode.nexts.add(fromNode);
                            break;
                        }
                    }
                    // 这一列
                    for (int k = i + 1; k < table.length; k++) {
                        if (table[k][j].countZero || table[k][j].count != 0 || (k == mainI && j == mainJ)) {
                            if (!graph.nodes.containsKey(table[k][j])) {
                                graph.nodes.put(table[k][j], new Node(table[k][j]));
                            }
                            Node toNode = graph.nodes.get(table[k][j]);
                            Edge edge = new Edge(fromNode, toNode);
                            graph.edges.add(edge);
                            fromNode.edges.add(edge);
                            toNode.edges.add(edge);
                            fromNode.du++;
                            toNode.du++;
                            fromNode.nexts.add(toNode);
                            toNode.nexts.add(fromNode);
                            break;
                        }

                    }
                }
            }
        }
        return graph;
    }


    //
    // // chaoxiang: 0 上， 1 下， 2 左， 3 右
    // public static boolean closingLoop(TableDate[][] table, Stack<Integer[]> path, int startI, int startJ,
    //                                   int i, int j, int deep, int chaoxiang) {
    //     if (i < 0 || i >= table.length || j < 0 || j >= table[0].length) {
    //         return false;
    //     }
    //     TableDate cur = table[i][j];
    //     if (deep == 0) {
    //         path.push(new Integer[]{i, j});
    //         if (!closingLoop(table, path, startI, startJ, i + 1, j, deep + 1, 0)) {
    //             if (!closingLoop(table, path, startI, startJ, i - 1, j, deep + 1, 1)) {
    //                 if (!closingLoop(table, path, startI, startJ, i, j + 1, deep + 1, 2)) {
    //                     return closingLoop(table, path, startI, startJ, i, j - 1, deep + 1, 3);
    //                 }
    //             }
    //         }
    //     } else if (i == startI && j == startJ) {
    //         return true;
    //     } else if (cur.count == 0 && !cur.countZero) {
    //         switch (chaoxiang) {
    //             case 0:
    //                 return closingLoop(table, path, startI, startJ, i + 1, j, deep + 1, chaoxiang);
    //             case 1:
    //                 return closingLoop(table, path, startI, startJ, i - 1, j, deep + 1, chaoxiang);
    //             case 2:
    //                 return closingLoop(table, path, startI, startJ, i, j + 1, deep + 1, chaoxiang);
    //             case 3:
    //                 return closingLoop(table, path, startI, startJ, i, j - 1, deep + 1, chaoxiang);
    //         }
    //     } else {
    //         if (chaoxiang == 0) {
    //             if (!closingLoop(table, path, startI, startJ, i + 1, j, deep + 1, 0)) {
    //                 path.push(new Integer[]{i, j});
    //                 if (!closingLoop(table, path, startI, startJ, i, j + 1, deep + 1, 2)) {
    //                     if (closingLoop(table, path, startI, startJ, i, j - 1, deep + 1, 3)) {
    //                         return true;
    //                     } else {
    //                         path.pop();
    //                         return false;
    //                     }
    //                 }
    //             }
    //         } else if (chaoxiang == 1) {
    //             if (!closingLoop(table, path, startI, startJ, i - 1, j, deep + 1, 1)) {
    //                 path.push(new Integer[]{i, j});
    //                 if (!closingLoop(table, path, startI, startJ, i, j + 1, deep + 1, 2)) {
    //                     if (closingLoop(table, path, startI, startJ, i, j - 1, deep + 1, 3)) {
    //                         return true;
    //                     } else {
    //                         path.pop();
    //                         return false;
    //                     }
    //                 }
    //             }
    //         } else if (chaoxiang == 2) {
    //             if (!closingLoop(table, path, startI, startJ, i, j + 1, deep + 1, 2)) {
    //                 path.push(new Integer[]{i, j});
    //                 if (!closingLoop(table, path, startI, startJ, i - 1, j, deep + 1, 1)) {
    //                     if (closingLoop(table, path, startI, startJ, i + 1, j, deep + 1, 0)) {
    //                         return true;
    //                     } else {
    //                         path.pop();
    //                         return false;
    //                     }
    //                 }
    //             }
    //         } else {
    //             if (!closingLoop(table, path, startI, startJ, i, j - 1, deep + 1, 3)) {
    //                 path.push(new Integer[]{i, j});
    //                 if (!closingLoop(table, path, startI, startJ, i - 1, j, deep + 1, 1)) {
    //                     if (closingLoop(table, path, startI, startJ, i + 1, j, deep + 1, 0)) {
    //                         return true;
    //                     } else {
    //                         path.pop();
    //                         return false;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }

    // 在没有被删除的行列里找到一个最小元素
    public static int[] findMinElementUnDeleted(double[][] matrix, Set<Integer> deletedRow, Set<Integer> deletedCol) {
        int minRow = 0;
        int minCol = 0;
        double minElement = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (deletedRow.contains(i)) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (deletedCol.contains(j)) {
                    continue;
                }
                if (matrix[i][j] < minElement) {
                    minElement = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }
        return new int[]{minRow, minCol};
    }

    // 根据找到的最小元素，更新产量和销量，更新删除的行号列号集合，最小元素添加进table里
    public static void updateTable(double[][] matrix, double[] production, double[] sales, TableDate[][] table,
                                   int[] minIndex, Set<Integer> deletedRow, Set<Integer> deletedCol,
                                   int insertNumCount, int outCount) {
        int row = minIndex[0];
        int col = minIndex[1];

        // 取剩余产量和剩余销量中较小的那个
        double min = Math.min(production[row], sales[col]);
        table[row][col].count = min;

        production[row] -= min;
        sales[col] -= min;

        if (production[row] == 0) {
            deletedRow.add(row);
        }
        if (sales[col] == 0) {
            deletedCol.add(col);
        }

        if (production[row] == 0 && sales[col] == 0 && insertNumCount + 1 != outCount) {
            for (int i = 0; i < matrix.length; i++) {
                if (table[i][col].count == 0) {
                    table[i][col].countZero = true;
                    break;
                }
            }
            for (int i = 0; i < matrix[0].length; i++) {
                if (table[row][i].count == 0) {
                    table[row][i].countZero = true;
                    break;
                }
            }
        }
    }

    // 打印表
    public static void printTable(TableDate[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + "     \t");
            }
            System.out.println();
        }
    }
}
