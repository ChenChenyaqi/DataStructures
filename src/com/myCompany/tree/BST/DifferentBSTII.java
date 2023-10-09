package com.myCompany.tree.BST;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class DifferentBSTII {
    /*
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
     * 可以按 任意顺序 返回答案。*/
    @Test
    public void test(){
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        // 到达空节点
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            // 得到以i为根节点的左子树集合和右子树集合
            List<TreeNode> leftTree = generateTrees(start, i - 1);
            List<TreeNode> rightTree = generateTrees(i + 1, end);
            // 分别从左子树集合和右子树集合中选出一个与当前根节点进行组合
            for (TreeNode left : leftTree){
                for (TreeNode right : rightTree){
                    TreeNode current = new TreeNode(i);
                    current.left = left;
                    current.right = right;
                    res.add(current);
                }
            }
        }
        return res;
    }
}
