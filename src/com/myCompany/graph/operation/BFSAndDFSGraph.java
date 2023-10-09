package com.myCompany.graph.operation;

import com.myCompany.graph.mygraph.Graph;
import com.myCompany.graph.mygraph.Node;

import java.util.*;

/**
 * 图的宽度有限遍历
 * @author Chen Yaqi
 * @version 1.0
 */
public class BFSAndDFSGraph {
    public static void main(String[] args) {
        Graph graph = new Graph();
        HashMap<Integer, Node> nodes = new HashMap<>();
        // 所以节点
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        // node0
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node4);
        node0.nexts = nodeList;
        // node1
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        nodeList.add(node2);
        node1.nexts = nodeList;
        // node2
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node3);
        nodeList.add(node4);
        node2.nexts = nodeList;
        // node3
        nodeList = new ArrayList<>();
        nodeList.add(node2);
        nodeList.add(node4);
        node3.nexts = nodeList;
        // node4
        nodeList = new ArrayList<>();
        nodeList.add(node0);
        nodeList.add(node2);
        nodeList.add(node3);
        node4.nexts = nodeList;

        nodes.put(0,node0);
        nodes.put(1,node1);
        nodes.put(2,node2);
        nodes.put(3,node3);
        nodes.put(4,node4);

        graph.nodes = nodes;

        // bfs
        bfs(node0);
        System.out.println("============");
        // dfs
        dfs(node0);
    }

    /**
     * 从Node节点出发的bfs
     * @param node 图中的一个节点
     */
    public static void bfs(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> hashSet = new HashSet<>();
        queue.add(node);
        hashSet.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value + " ");
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                if (!hashSet.contains(next)) {
                    queue.add(next);
                    hashSet.add(next);
                }
            }
        }
    }

    /**
     * 从node出发的深度优先遍历
     * @param node 图中一节点
     */
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> hashSet = new HashSet<>();
        stack.push(node);
        hashSet.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts){
                if (!hashSet.contains(next)){
                    // 重新把cur入栈
                    stack.push(cur);
                    // next入栈
                    stack.push(next);
                    System.out.println(next.value);
                    hashSet.add(next);
                    // 跳过其他相邻的节点，去处理next
                    break;
                }
            }
        }
    }
}
