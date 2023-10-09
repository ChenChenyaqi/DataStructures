package com.myCompany.bfs;

/**
 * @author chenyaqi
 * @date 2021/8/8 - 10:00
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(1);
        q.left = new TreeNode(1);
        q.right = new TreeNode(2);

        boolean sameTree = isSameTree(p, q);
        System.out.println("sameTree = " + sameTree);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p.val != q.val){
            return false;
        }
        return true;
    }
}

class TreeNode {
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
