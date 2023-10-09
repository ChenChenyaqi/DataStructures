package com.newDataStructures.graphAbout;

import com.newDataStructures.graphAbout.SetStructure.MySets;
import com.newDataStructures.graphAbout.graphStructure.Edge;
import com.newDataStructures.graphAbout.graphStructure.Graph;
import com.newDataStructures.graphAbout.graphStructure.Node;

import java.util.*;

/**
 * 最小生成树
 */
public class MST {

    static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * K算法
     *
     * @param graph 图
     * @return 边的Set
     */
    public static Set<Edge> KruskalMST(Graph graph) {
        ArrayList<Node> tempNodes = new ArrayList<>(graph.nodes.values());
        MySets mySets = new MySets(tempNodes);

        // 小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!mySets.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                mySets.union(edge.from, edge.to);
            }
        }
        return res;
    }

    /**
     * P 算法
     *
     * @param graph 图
     * @return 边Set
     */
    public static Set<Edge> PrimMST(Graph graph) {
        Set<Node> usedSet = new HashSet<>();
        Set<Edge> res = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        // 最外层的for循环用于处理 森林 问题
        for (Node node : graph.nodes.values()) {
            // 新的 节点
            if (!usedSet.contains(node)) {
                // 如果没有看过，就加入进去
                usedSet.add(node);
                // 解锁这个节点的所有边
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    // 弹出解锁边中最小的边
                    Edge edge = priorityQueue.poll();
                    // 可能的新的节点
                    Node toNode = edge.to;
                    if (!usedSet.contains(toNode)) {
                        usedSet.add(toNode);
                        res.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return res;
    }
}
