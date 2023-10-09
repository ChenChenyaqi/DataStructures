package com.operationsResearch.connectionNumbers.minPath;

import java.util.ArrayList;
import java.util.List;

public class AntiCircleFun {
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
        Graph graph = getGraph(matrix);
        List<Edge> minPath = getMinPath(graph, graph.nodes.get(1), graph.nodes.get(6));
        System.out.println(minPath);
    }

    // 输入起点和终点，返回一条从起点到终点的路径（边的集合）
    public static List<Edge> getMinPath(Graph graph, Node startNode, Node endNode) {
        if (!graph.nodes.containsValue(startNode) || !graph.nodes.containsValue(endNode)) {
            System.out.println("输入的起点和终店有误！");
            return new ArrayList<>();
        }
        List<Edge> res = new ArrayList<>();
        // 初始化起点的sumMin 和 preNode
        startNode.sumMin = 0;
        startNode.preNode = null;
        // 反圈集合
        // 初始化反圈集合
        List<Edge> antiCircleList = new ArrayList<>(startNode.edges);
        while (true) {
            // 从反圈集合中选择权重+sumMin最小的那个
            Edge edge = findMinInAntiCircle(antiCircleList);
            // 设置preNode
            edge.to.preNode = edge.from;
            // 设置minSum
            edge.to.sumMin = edge.from.sumMin + edge.weight;
            if (edge.to == endNode) {
                break;
            }
            // 移除所选的边
            antiCircleList.remove(edge);
            // 解锁新的边加入到反圈集合中
            for (Edge e : edge.to.edges) {
                antiCircleList.add(e);
            }
        }
        // 递归按照preNode寻找路径
        Node cur = endNode;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            cur = cur.preNode;
            if(cur != null){
                Edge edge = new Edge(0, cur, pre);
                res.add(edge);
            }
        }
        // 逆序
        List<Edge> r = new ArrayList<>(res.size());
        for (int i = res.size() - 1; i >= 0; i--) {
            r.add(res.get(i));
        }
        return r;
    }

    // 找到反圈中sumMin+权重最小的那个边
    private static Edge findMinInAntiCircle(List<Edge> antiCircleList) {
        int min = 999;
        Edge res = null;
        for (Edge e : antiCircleList) {
            if (e.from.sumMin + e.weight < min) {
                min = e.from.sumMin + e.weight;
                res = e;
            }
        }
        return res;
    }


    // 生成一个图
    private static Graph getGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            if (!graph.nodes.containsKey(matrix[i][0])) {
                graph.nodes.put(matrix[i][0], new Node(matrix[i][0]));
            }
            if (!graph.nodes.containsKey(matrix[i][1])) {
                graph.nodes.put(matrix[i][1], new Node(matrix[i][1]));
            }
            Node fromNode = graph.nodes.get(matrix[i][0]);
            Node toNode = graph.nodes.get(matrix[i][1]);
            Edge edge = new Edge(matrix[i][2], fromNode, toNode);
            graph.edges.add(edge);
            fromNode.edges.add(edge);
            fromNode.nexts.add(toNode);
        }
        return graph;
    }
}
