package com.myCompany.graph.operation;

import com.myCompany.graph.mygraph.Edge;
import com.myCompany.graph.mygraph.Graph;
import com.myCompany.graph.mygraph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 求最短路径的算法
 * Dijkstra算法: 边的权重不能为负；由一个点出发，到其他点的最短距离
 * floyd算法：
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class ShortestPath {
    public static void main(String[] args) {
        // matrix[0][0] = weight 权重; matrix[0][1] = fromNode 从哪个节点来; matrix[0][2] = toNode 到哪个节点去;
        int[][] matrix = new int[][]{
                {5, 0, 1}, {5, 1, 0},
                {20, 0, 2}, {20, 2, 0},
                {17, 0, 3}, {17, 3, 0},
                {2, 1, 2}, {2, 2, 1},
                {10, 1, 4}, {10, 4, 1},
                {1, 2, 3}, {1, 3, 2},
                {5, 3, 4}, {5, 4, 3}
        };
        Graph graph = ConversionInterface.conversion(matrix);
        // Dijkstra
        HashMap<Node, Integer> map = dijkstra(graph.nodes.get(0));
        for (Map.Entry entry : map.entrySet()) {
            Node node = (Node)entry.getKey();
            int value = (int)entry.getValue();
            System.out.println(node.value + " : " + value);
        }
        System.out.println("============================");
        // 堆实现法
        HashMap<Node, Integer> map2 = dijkstraByHeap(graph.nodes.get(0), 5);
        for (Map.Entry entry : map2.entrySet()) {
            Node node = (Node)entry.getKey();
            int value = (int)entry.getValue();
            System.out.println(node.value + " : " + value);
        }
    }

    /**
     * Dijkstra算法求head到各个节点的最短距离
     *
     * @param head 出发节点
     * @return HashMap
     */
    public static HashMap<Node, Integer> dijkstra(Node head) {
        // 从head到所有点的最小距离
        // key-从head到node节点，value-从head到node节点的最短距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // head到head距离为0
        distanceMap.put(head, 0);
        // 已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        // 在在没有求过距离的节点中，选出一个距离最小的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 得到minNode的distance
            int distance = distanceMap.get(minNode);
            // 遍历它的所有边
            for (Edge edge : minNode.edges) {
                // 一条边的toNode
                Node toNode = edge.to;
                // 如果toNode不在distanceMap中
                if (!distanceMap.containsKey(toNode)) {
                    // 直接加入
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果toNode在里面，比较大小，换成最小的那个
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 选中minNode
            selectedNodes.add(minNode);
            // 再选一个
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 在未选择的节点中，选出一个距离最小的节点
     *
     * @param distanceMap   距离head的所有节点及距离
     * @param selectedNodes 已经选择过的节点
     * @return Node
     */
    private static Node getMinDistanceAndUnselectedNode(
            HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
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

    // =====================================================================================================


    // 通过小根堆实现 每次选择距离最短的那个Node, size:总共有多少节点
    public static HashMap<Node, Integer> dijkstraByHeap(Node head, int size) {
        // 传入size初始化 堆的大小
        NodeHeap nodeHeap = new NodeHeap(size);
        // 加入或更新或忽略：如果有一个节点的记录是第一次出现，则add
        //                  如果这个节点有过，且比之前的距离更小，则update
        //                  如果这个节点有个，且没有比之前的距离小，则ignore
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        // 存放head到各个节点的距离
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;

    }

    // 小根堆
    private static class NodeHeap {
        // 所以的节点放在数组里
        private Node[] nodes;
        // 查找nodes[i]在堆的什么位置，value：node在堆上的index
        private HashMap<Node, Integer> headIndexMap;
        // node到head的目前的最短距离
        private HashMap<Node, Integer> distanceMap;
        // 目前堆上一共有多少节点
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            headIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        // 加入node或更新或忽略
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // 如果有node，就比较替换
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(headIndexMap.get(node));
            }
            // 如果没有，就加入
            if (!isEntered(node)) {
                nodes[size] = node;
                headIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }

        // 弹出堆顶的那个
        public NodeRecord pop() {
            // 得到堆顶
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            // 交换堆顶和最后一个元素
            swap(0, size - 1);
            // 把最后一个元素设置为不在堆里
            headIndexMap.put(nodes[size - 1], -1);
            // 去除最后一个元素
            distanceMap.remove(nodes[size - 1]);
            // 设置为null
            nodes[size - 1] = null;
            // 从堆顶开始堆化
            heapify(0, --size);
            // 返回nodeRecord
            return nodeRecord;
        }

        // 向堆中插入nodes[index]
        private void insertHeapify(int index) {
            // 插入的去和它上面的比较，决定它要不要往上移动
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 小根堆化: 以index为根的子树
        private void heapify(int index, int size) {
            // index的左孩子
            int left = index * 2 + 1;
            while (left < size) {
                // 在左孩子和右孩子中选一个最小的
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1 : left;
                // 再和index比较
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                // 再往下接着去堆化
                index = smallest;
                left = index * 2 + 1;
            }
        }

        // 判断堆是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 判断node是否进入过小根堆
        private boolean isEntered(Node node) {
            return headIndexMap.containsKey(node);
        }

        // 判断node现在是否还在小根堆里
        private boolean inHeap(Node node) {
            // headIndexMap会把现在不在堆里的node的value设置为-1
            return isEntered(node) && headIndexMap.get(node) != -1;
        }

        // 交换堆中两个索引位置的元素
        private void swap(int index1, int index2) {
            headIndexMap.put(nodes[index1], index2);
            headIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // head 到 node 的最短距离
    private static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
