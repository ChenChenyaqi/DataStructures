package com.operationsResearch.connectionNumbers.networkmaxflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NetworkMaxFlow {
    public static void main(String[] args) {
        // [i][0]: from, [i][1]: to
        // [i][2]: cij 容量
        // [i][3]: fij 流量
        int[][] matrix = new int[][]{
                {1, 2, 4, 3},
                {1, 3, 4, 4},
                {1, 6, 3, 2},
                {2, 6, 3, 2},
                {2, 4, 1, 1},
                {3, 6, 3, 2},
                {3, 5, 4, 2},
                {4, 7, 7, 6},
                {5, 7, 8, 3},
                {5, 4, 2, 2},
                {6, 4, 4, 3},
                {6, 5, 5, 3},
        };

        Graph graph = createGraph(matrix);

        int maxFlow = getMaxFlow(graph, 1, 7);
    }

    private static int getMaxFlow(Graph graph, int start, int end) {
        if (graph == null || !graph.nodes.containsKey(start) || !graph.nodes.containsKey(end)) {
            return -1;
        }

        Node startNode = graph.nodes.get(start);
        startNode.p = 0;
        startNode.theta = 9999;
        Node endNode = graph.nodes.get(end);
        // 已经选择的点
        Set<Node> X = new HashSet<>();
        X.add(startNode);
        // 正向反圈
        Set<Edge> antiCircle1 = new HashSet<>();
        // 负向反圈
        Set<Edge> antiCircle2 = new HashSet<>();

        // 初始化
        // 加入正向反圈
        for (Edge edge : startNode.edges) {
            if (edge.f < edge.c) {
                antiCircle1.add(edge);
                edge.to.p = edge.from.value;
                edge.to.theta = Math.min(edge.from.theta, edge.c - edge.f);
            }
        }

        while (!X.contains(endNode)) {
            // 把所有非饱和弧加入正向反圈中
            List<Edge> edgeList1 = getNotFullEdge(X, antiCircle1, antiCircle2);
            for (Edge edge : edgeList1) {
                antiCircle1.add(edge);
            }
        }


        return 0;
    }

    private static List<Edge> getNotFullEdge(Set<Node> X, Set<Edge> antiCircle1, Set<Edge> antiCircle2) {
        List<Edge> res = new ArrayList<>();
        if (antiCircle1.size() == 0 && antiCircle2.size() == 0) {
            return res;
        }
        if (antiCircle1.size() > 0) {
            for (Edge edge : antiCircle1) {
                for (Edge e : edge.to.edges) {
                    if (e.f < e.c && !X.contains(e.to)) {
                        res.add(e);
                    }
                }
            }
        }
        if (antiCircle2.size() > 0) {
            for (Edge edge : antiCircle2) {
                for (Edge e : edge.from.edges) {
                    if (e.f < e.c && !X.contains(e.from)) {
                        res.add(e);
                    }
                }
            }
        }

        return res;
    }

    private static Graph createGraph(int[][] matrix) {
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
            Edge edge = new Edge(matrix[i][2], matrix[i][3], fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
