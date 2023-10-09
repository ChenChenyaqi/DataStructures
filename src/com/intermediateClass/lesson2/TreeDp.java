package com.intermediateClass.lesson2;

/**
 * 二叉树每个节点都有一个 int 型权值，给定一个二叉树，要求计算出从根节点到叶节点的所有路径中，权值和最大的值是多少？
 */
public class TreeDp {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(Node head) {
        p(head, 0);
        return maxSum;
    }

    // pre: 从根节点出发 到 当前节点 上方的节点，获得的路径和
    private static void p(Node x, int pre) {
        if (x.left == null && x.right == null) { // 当前是叶节点
            maxSum = Math.max(maxSum, pre + x.value);
            return;
        }
        if (x.left != null) {
            p(x.left, pre + x.value);
        }
        if (x.right != null) {
            p(x.right, pre + x.value);
        }
    }

    // 套路解法
    public static int maxDis(Node head){
        if(head== null){
            return 0;
        }
        return process2(head);
    }

    // 以x为头的整棵树上，最大路径和是多少，返回
    private static int process2(Node x) {
        if(x.left == null && x.right == null){
            return x.value;
        }
        int next = Integer.MIN_VALUE;
        if(x.left != null){
            next = process2(x.left);
        }
        if(x.right != null){
            next = Math.max(next, process2(x.right));
        }
        return x.value + next;
    }


}
