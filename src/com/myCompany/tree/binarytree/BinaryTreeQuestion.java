package com.myCompany.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 非递归遍历二叉树
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class BinaryTreeQuestion {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        // 前序遍历
        preOrderUnRecur(head);
        // 中序
        inOrderUnRecur(head);
        // 层序遍历
        sequenceTraversal(head);
        // 判断是否为完全二叉树
        head.right.right = new TreeNode(6);
        boolean cbt = isCBT(head);
        System.out.println("cbt = " + cbt);
        // 打印折痕
        printAllFolds(3);
    }

    //================================================================================
    // （微软） 打印一张纸折叠n次后，从上（手指一直捏着的一端）到下  依次出现的  折痕凹凸情况
    // 例如： 折一次：凹
    //      折两次：凹   凹   凸
    //      折三次：凹   凹   凸   凹   凹   凸   凸
    public static void printAllFolds(int N) {
        if (N < 1){
            System.out.println("没有折叠");
            return;
        }
        printProcess(1, N, true);
    }

    // i为当前层数，N为折多次，也就是一共多少层，down：true为凹，false为凸
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }


    //================================================================================
    // 二叉树的序列化与反序列化

    // 序列化成字符串: 以前序遍历
    public static String serialByPre(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String res = root.val + "_";
        res += serialByPre(root.left);
        res += serialByPre(root.right);
        return res;
    }

    // 反序列化成TreeNode
    public static TreeNode reconByPreString(String preStr) {
        if (preStr == null) {
            return null;
        }
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    private static TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


    //================================================================================
    // 判断是否为满二叉树
    public static boolean isFST(TreeNode root) {
        Info info = f(root);
        return info.nodes == 1 << info.height - 1;
    }

    private static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info f(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info left = f(root.left);
        Info right = f(root.right);
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info(height, nodes);
    }


    //================================================================================
    // 判断是否为二叉搜索树
    // 套路(树型DP)：如果我知道，我的左子树是否为BST，我的右子树是否为BST，并且左子树的max和右子树的min
    //      那么，我就可以判断我是否为BST，以及加上我后的min和max
    //       但递归要求返回值都一样，那全都返回，是否BST，min,max这三个值
    public static boolean isBST(TreeNode root) {
        return bstProcess(root).isBST;
    }

    private static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    private static ReturnData bstProcess(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 得到左右子树的数据
        ReturnData leftReturn = bstProcess(root.left);
        ReturnData rightReturn = bstProcess(root.right);
        // 写返回值逻辑
        int max = root.val;
        int min = root.val;
        if (leftReturn != null) {
            max = Math.max(leftReturn.max, max);
            min = Math.min(leftReturn.min, min);
        }
        if (rightReturn != null) {
            max = Math.max(rightReturn.max, max);
            min = Math.min(rightReturn.min, min);
        }
        boolean isBST = true;
        if (leftReturn != null && (!leftReturn.isBST || leftReturn.max >= root.val)) {
            isBST = false;
        }
        if (rightReturn != null && (!rightReturn.isBST || rightReturn.min <= root.val)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }


    //================================================================================
    // 判断是否是平衡二叉树
    // 套路(树型DP)：分析判断是否为平衡二叉树的条件：1.左右子树高度差不大于1。2、左右子树都是平衡二叉树
    //       分析验证条件所需的值：1、左子树的高度和是isBalanced。2、右子树的高度和是isBalanced
    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }
        // 得到左右子树的返回值
        ReturnType returnLeft = process(root.left);
        ReturnType returnRight = process(root.right);
        // 拆解黑盒，写height和isBalanced的逻辑
        int height = Math.max(returnLeft.height, returnRight.height) + 1;
        boolean isBalanced = returnLeft.isBalanced && returnRight.isBalanced &&
                Math.abs(returnLeft.height - returnRight.height) < 2;
        // 返回自己的返回值
        return new ReturnType(isBalanced, height);
    }


    //================================================================================
    // 判断是否为完全二叉树
    public static boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 标记是否遇到过左右孩子不双全的情况
        boolean flag = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if (
                // 此节点是       非叶节点
                    (flag && !(l == null && r == null))
                            ||
                            // 左空右不空
                            (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            // 如果左右孩子不双全
            if (l == null || r == null) {
                flag = true;
            }
        }
        return true;
    }

    //================================================================================
    // 二叉树层序遍历
    public static void sequenceTraversal(TreeNode head) {
        System.out.println("层序遍历：");
        if (head != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                int size = queue.size();
                System.out.print(" |" + size + "| ");
                for (int i = 0; i < size; i++) {
                    head = queue.poll();
                    System.out.print(head.val + " ");
                    if (head.left != null) {
                        queue.add(head.left);
                    }
                    if (head.right != null) {
                        queue.add(head.right);
                    }
                }
            }
        }
        System.out.println();
    }

    //================================================================================
    // 二叉树的非递归前序遍历
    public static void preOrderUnRecur(TreeNode head) {
        System.out.println("非递归前序遍历：");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");
                if (head.right != null) {
                    stack.add(head.right);
                }
                if (head.left != null) {
                    stack.add(head.left);
                }
            }
        }
        System.out.println();
    }

    //================================================================================
    // 二叉树的非递归中序遍历：
    // 整个树左边界入栈，依次弹出过程中，右子树入栈
    public static void inOrderUnRecur(TreeNode head) {
        System.out.println("非递归中序遍历：");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }
}
