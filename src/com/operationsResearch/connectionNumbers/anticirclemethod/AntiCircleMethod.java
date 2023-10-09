package com.operationsResearch.connectionNumbers.anticirclemethod;

import com.operationsResearch.connectionNumbers.minPath.Edge;
import com.operationsResearch.connectionNumbers.minPath.Graph;
import com.operationsResearch.connectionNumbers.minPath.Node;

import java.util.*;

public class AntiCircleMethod {

    public static void main(String[] args) {
        // matrix[i][0] from, matrix[i][1] to, matrix[i][2] weight
        int[][] matrix = new int[][]{
                {1, 2, 5},
                {1, 3, 6},
                {2, 3, 1},
                {2, 4, 2},
                {2, 5, 7},
                {3, 5, 5},
                {4, 5, 3},
                {4, 6, 4},
                {5, 6, 4},
        };

        // 根据矩阵构造图
        Graph graph = createGraph(matrix);
        // 调用反圈法（prim算法）返回最小生成树集合
        Set<Edge> edges = primMST(graph);
        System.out.println(Arrays.toString(edges.toArray()));
    }


    // 反圈法返回最小生成树
    public static Set<Edge> primMST(Graph graph) {
        // 小根堆，每次加入一个edge，保证堆顶是edge值最小的
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeCompare());
        // 使用过的点
        Set<Node> usedSet = new HashSet<>();
        // 使用过的边
        Set<Edge> usedEdge = new HashSet<>();
        // MST结果
        Set<Edge> res = new HashSet<>();

        // 最外层的for循环用于处理 森林 问题
        for (Node node : graph.nodes.values()) {
            // 新的 节点
            if (!usedSet.contains(node)) {
                // 如果没有看过，就加入进去
                usedSet.add(node);
                // 解锁这个节点的所有边
                for (Edge edge : node.edges) {
                    // 如果没有访问过这个边，就加入小根堆
                    if (!usedEdge.contains(edge)) {
                        usedEdge.add(edge);
                        priorityQueue.add(edge);
                    }
                }
                while (!priorityQueue.isEmpty()) {
                    // 弹出解锁边中最小的边
                    Edge edge = priorityQueue.poll();
                    // 可能的新的节点
                    Node toNode = edge.to;
                    if (!usedSet.contains(toNode)) {
                        usedSet.add(toNode);
                        // 结果中加入这个边
                        res.add(edge);
                        // 解锁新节点的所有边
                        for (Edge nextEdge : toNode.edges) {
                            // 如果没有访问过这个边，就加入小根堆
                            if (!usedEdge.contains(nextEdge)) {
                                usedEdge.add(nextEdge);
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }

        return res;
    }


    public static class EdgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    /**
     * 生成一个无向图
     *
     * @param matrix matrix[0][0] from, matrix[0][1] to, matrix[0][2] weight
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            toNode.nexts.add(fromNode);
            fromNode.edges.add(edge);
            toNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
