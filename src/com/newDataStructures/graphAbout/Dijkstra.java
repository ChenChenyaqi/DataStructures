package com.newDataStructures.graphAbout;

import com.newDataStructures.graphAbout.graphStructure.Edge;
import com.newDataStructures.graphAbout.graphStructure.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 求最短路径
 * <p>
 * Dijkstra算法，适用范围：没有权值为负的边
 * 规定出发点，出发点到任意节点的最短路径值
 */
public class Dijkstra {


    // Dijkstra算法
    public static HashMap<Node, Integer> dijkstra(Node head) {
        // 从 head 到 node 的最小距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        // 已经求过距离的节点
        HashSet<Node> selectedNodes = new HashSet<>();
        // 得到在 distanceMap 中 没有 selected 的 最小的距离的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    // 得到在 distanceMap 中 没有 selected 的 最小的距离的节点
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap,
                                                        HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }




    // 利用 堆 加速
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    private static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        // 没有就添加，有就更新记录值，不比之前的值小就忽略
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        // 存放记录，从head出发到各个节点的距离的最小值
        HashMap<Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            // 弹出最小的一个记录
            NodeRecord record = nodeHeap.pop();
            // 最小的节点
            Node cur = record.node;
            // 距离
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                // head 到 cur的距离 + cur 到 nextNode 的距离
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            res.put(cur, distance);
        }
        return res;
    }

    private static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // 堆
    private static class NodeHeap {
        // 所有的node
        private Node[] nodes;
        // 查一个 node 在堆的什么位置
        private HashMap<Node, Integer> heapIndexMap;
        // node 到 head 的最短距离
        private HashMap<Node, Integer> distanceMap;
        // 堆大小
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                // 如果 node 在堆里，则比较之前的距离 和 现在的距离
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                // 从 node 位置开始 向上 堆化
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                // 如果 node 第一次进入堆
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                // 从 node 位置开始 向上 堆化
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null; 
            heapify(0, --size);
            return nodeRecord;
        }

        // 表示 node 有没有进来过， 如果node不再nodes中了，但是node进来过，则它的 value = -1
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        // 判断 node 是不是还在 堆中
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        // 交换 堆 中 两个node 的位置
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

        // 在 index 位置插入 一个 node，向上 堆化
        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[((index - 1) / 2)])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从 index 开始 向下堆化
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ?
                        left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }
}
