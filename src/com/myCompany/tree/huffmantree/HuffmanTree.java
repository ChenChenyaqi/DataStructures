package com.myCompany.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/6/19 - 15:16
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }

    // 创建赫夫曼树的方法,返回赫夫曼树的root节点
    public static Node createHuffmanTree(int[] arr) {
        // 1.遍历arr数组
        // 2.将arr的每个元素构成一个Node
        // 3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        // 先进行排序,从小到大
        Collections.sort(nodes);
        while (nodes.size() > 1) {
            // 取出根节点权值最下的两个二叉树节点
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 构建一个新的二叉树
            Node parent = new Node(left.value + right.value, left, right);
            // 从ArrayList中删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // 向ArrayList中加入新的二叉树
            nodes.add(parent);
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }
}

// 创建节点类
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排
        return this.value - o.value;
    }
}
