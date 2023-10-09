package com.myCompany.tree.binarytree;

/**
 * 二叉树的经典面试题
 * @author Chen Yaqi
 * @version 1.0
 */
public class ClassicTopic {
    public static void main(String[] args) {

    }

    private static int pre = Integer.MIN_VALUE;

    // 是否是二叉搜索树 : 左树节点都比它小，右树节点都比它大
    public static boolean isBinarySearchTree(TreeNode root){
        if (root ==  null){
            return true;
        }
        boolean leftTree = isBinarySearchTree(root.left);
        if (!leftTree){
            return false;
        }
        if (root.val <= pre){
            return false;
        } else {
            pre = root.val;
        }
        return isBinarySearchTree(root.right);
    }
}


