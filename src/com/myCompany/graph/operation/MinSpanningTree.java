package com.myCompany.graph.operation;

import com.myCompany.graph.mygraph.Edge;
import com.myCompany.graph.mygraph.Graph;
import com.myCompany.graph.mygraph.Node;

import java.util.*;

/**
 * 最小生成树：求无向带权图，连通所以节点的，总权重最小的图
 * K算法：并查集，每次在所有边中选择最小的边
 * P算法: 无需并查集，逐次解锁点，在解锁的点、边中，选择最小的那个
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class MinSpanningTree {
    public static void main(String[] args) {
        // TODO:用并查集实现最小生成树

        // P算法
    }

    /**
     * 边比较器
     */
    public static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * P算法
     * @param graph 图
     * @return 选择的边
     */
    public static Set<Edge> primMST(Graph graph){
        if (graph == null || graph.nodes == null || graph.nodes.size() == 0){
            return null;
        }
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 存放选择过的点
        HashSet<Node> nodeSet = new HashSet<>();
        // 依次挑选的边存在edgeSet里
        HashSet<Edge> edgeSet = new HashSet<>();

        // 默认图是连通的，即不会出现森林，不然，则外面再加一个for循环遍历所有node
        Node node = new Node(0);
        // 随便选择一个点
        for (Node t : graph.nodes.values()){
            node = t;
            break;
        }
        // 把node放入nodeSet里，表示已经选择过了
        nodeSet.add(node);
        // 由node点出发的 所有边 加入到 最小堆里
        priorityQueue.addAll(node.edges);
        while (!priorityQueue.isEmpty()){
            // 弹出最小边
            Edge edge = priorityQueue.poll();
            // 得到这条边指向的点
            Node toNode = edge.to;
            // 若尚未选择这个点
            if (!nodeSet.contains(toNode)){
                nodeSet.add(toNode);
                edgeSet.add(edge);
                // 加入新解锁的边
                priorityQueue.addAll(toNode.edges);
            }
        }
        return edgeSet;
    }


}
