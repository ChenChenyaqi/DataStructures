package com.newDataStructures.graphAbout;

import com.newDataStructures.graphAbout.graphStructure.Graph;
import com.newDataStructures.graphAbout.graphStructure.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的拓扑排序
 */
public class SortedTopology {

    public static ArrayList<Node> sortedTopology(Graph graph){
        // key: 某一个node
        // value: 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为0的点，进入队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 进行拓扑排序
        ArrayList<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
