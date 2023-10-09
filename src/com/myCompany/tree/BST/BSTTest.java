package com.myCompany.tree.BST;

import org.junit.Test;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
// BST增删(改)查+判断合法性
public class BSTTest {

    @Test
    public void test1() {
        // 判断一个二叉树是不是BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15, new TreeNode(6), new TreeNode(20));

        boolean validBST = TreeNodes.isValidBST(root);
        System.out.println("validBST = " + validBST);
    }

    @Test
    public void test2() {
        // 向BST中增加数据,重复数据不会添加
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15, new TreeNode(11), new TreeNode(20));
        for (int val = 0; val < 30; val++) {
            TreeNodes.insertIntoBST(root, val);
        }

        TreeNodes.inOrder(root);
    }

    @Test
    public void test3() {
        // 在BST中删除特定节点
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15, new TreeNode(11), new TreeNode(20));
        // 删除存在的节点
        int flag1 = TreeNodes.deleteNode(root, 5);
        System.out.println("flag1 = " + flag1);

        int flag2 = TreeNodes.deleteNode(root, 15);
        System.out.println("flag2 = " + flag2);

        // 删除不存在的节点
        int flag3 = TreeNodes.deleteNode(root, 5);
        System.out.println("flag3 = " + flag3);

        TreeNodes.inOrder(root);
    }

    @Test
    public void test4() {
        // 修改BST中的节点，实际上是先删除后添加，因为不能在原来的基础上直接修改val
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15, new TreeNode(11), new TreeNode(20));
        // 给出要删除的值，以及要添加的值
        int oldVal = 15;
        int newVal = 8;
        int flag = TreeNodes.updateBST(root, oldVal, newVal);
        System.out.println("flag = " + flag);

        TreeNodes.inOrder(root);
    }

    @Test
    public void test5() {
        // 在BST中查找某个节点，存在返回该节点，否则返回null
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15, new TreeNode(11), new TreeNode(20));
        int val1 = 15;
        int val2 = 16;
        TreeNode node1 = TreeNodes.searchBST(root, val1);
        TreeNode node2 = TreeNodes.searchBST(root, val2);
        System.out.println("val1 = " + node1);
        System.out.println("node2 = " + node2);
    }
}

class TreeNodes {

    // 是否成功删除
    private static boolean isDeleted;

    /**
     * 中序遍历BST
     *
     * @param root BST根节点
     */
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + "->");
        inOrder(root.right);
    }

    /**
     * 判断一个二叉树是否为BST
     *
     * @param root 二叉树根节点
     * @return true表示是BST，false表示不是
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    /**
     * 向BST中添加节点
     *
     * @param root BST根节点
     * @param val  要添加的节点
     * @return 返回添加后的BST
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val == val) {
            return root;
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 在BST中删除指定元素
     *
     * @param root BST根节点
     * @param val  要删除的元素
     * @return 返回成功删除数，1表示成功删除，-1表示没有
     */
       public static int deleteNode(TreeNode root, int val) {
        deleteTreenode(root, val);
        if (isDeleted) {
            isDeleted = false;
            return 1;
        } else {
            return -1;
        }
    }

    private static TreeNode deleteTreenode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // 成功删除了
            isDeleted = true;
            // 如果此节点不是一个二叉树的根节点
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 如果此节点是子树的根节点
            TreeNode minNode = getMinNode(root.right);
            root.val = minNode.val;
            root.right = deleteTreenode(root.right, minNode.val);
        } else if (root.val < key) {
            root.right = deleteTreenode(root.right, key);
        } else {
            root.left = deleteTreenode(root.left, key);
        }
        return root;
    }

    private static TreeNode getMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 删除oldVal，添加newVal
     *
     * @param root   BST根节点
     * @param oldVal 旧值
     * @param newVal 新值
     * @return 成功操作后返回1，没有成功返回-1，说明不存在oldVal
     */
    public static int updateBST(TreeNode root, int oldVal, int newVal) {
        // 成功删除后返回1
        int flag = deleteNode(root, oldVal);
        insertIntoBST(root, newVal);
        return flag;
    }

    /**
     * 查找BST中某个节点
     *
     * @param root BST根节点
     * @param val  要查找的节点
     * @return 存在返回该节点，否则返回null
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}