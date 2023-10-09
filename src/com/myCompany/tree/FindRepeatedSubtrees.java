package com.myCompany.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenyaqi
 * @version 1.0
 * @date 2021/8/8 - 18:54
 */
public class FindRepeatedSubtrees {
    /*给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
两棵树重复是指它们具有相同的结构以及相同的结点值。*/
    public static void main(String[] args) {
        FindRepeatedSubtrees frs = new FindRepeatedSubtrees();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);

    }
    // 结果集
    public List<TreeNode> res = new LinkedList<>();
    // map
    public HashMap<String, Integer> map = new HashMap<>();

    /**
     * 找出二叉树中所有重复的子树
     * @param root 根节点
     * @return 重复子树根节点组成的集合
     */
    public  List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    // 序列化每个子树

    /**
     * 序列化每个子树
     * @param root root根节点
     * @return 序列化的字符串形式
     */
    private String traverse(TreeNode root) {
        if(root == null){
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = map.getOrDefault(subTree, 0);
        if (freq == 1){
            res.add(root);
        }
        map.put(subTree, freq + 1);
        return subTree;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}



