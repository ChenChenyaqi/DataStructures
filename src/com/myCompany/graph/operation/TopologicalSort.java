package com.myCompany.graph.operation;

import com.myCompany.graph.mygraph.Graph;
import com.myCompany.graph.mygraph.Node;

import java.util.*;

/**
 * 拓扑排序
 * 案例：jar包的互相依赖，A包依赖B包、C包、D包；B包依赖E包、C包
 *    A
 *  ^  ^  ^
 * /   |   \
 * B<——C<——D
 * ^      ^
 * |     /
 * |    /
 * |  /
 *  E
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class TopologicalSort {
    public static void main(String[] args) {
        Graph graph = new Graph();
        HashMap<Integer, Node> nodes = new HashMap<>();
        // 所以节点
        Node node0 = new Node(0);
        node0.in = 3;
        Node node1 = new Node(1);
        node1.in = 2;
        Node node2 = new Node(2);
        node2.in = 1;
        Node node3 = new Node(3);
        node3.in = 1;
        Node node4 = new Node(4);
        node4.in = 0;
        // node0
        ArrayList<Node> nodeList;
        // node1
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        node1.nexts = nodeList;
        // node2
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        nodeList.add(node1);
        node2.nexts = nodeList;
        // node3
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        nodeList.add(node2);
        node3.nexts = nodeList;
        // node4
        nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node3);
        node4.nexts = nodeList;

        nodes.put(0,node0);
        nodes.put(1,node1);
        nodes.put(2,node2);
        nodes.put(3,node3);
        nodes.put(4,node4);

        graph.nodes = nodes;

        // 拓扑排序
        List<Node> resList = topologicalSort(graph);
        for (Node node : resList){
            System.out.println(node.value);
        }
    }

    /**
     * 对一个有向图进行拓扑排序
     * @param graph 图
     * @return 排序结果
     */
    public static List<Node> topologicalSort(Graph graph) {
        // key-某个Node，value-其剩余入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为0，才能进队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0){
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入result中
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
