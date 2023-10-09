package com.myCompany.tree;

import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/7/31 - 21:10
 */
public class VerticalOrderTraversalOfBinaryTree {
    // Node节点为Key，节点的行、列坐标以及 值 为Value
    public static Map<TreeNode, int[]> map = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


        map.put(root, new int[]{0, 0, root.val});
        // 前序遍历
        dfs(root);

        List<int[]> list = new ArrayList<>(map.values());
        // 排序，先列 后行 后值
        Collections.sort(list,(a,b) ->{
            if (a[1] != b[1]){
                return a[1] - b[1];
            }
            if (a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[2] - b[2];
        });

        // 寻找列一样的，放一起
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < list.size();) {
            List<Integer> temp = new ArrayList<>();
            int j = i;
            for (; j < list.size(); j++) {
                if (list.get(i)[1] == list.get(j)[1]){
                    temp.add(list.get(j)[2]);
                } else {
                    break;
                }
            }
            res.add(temp);
            i = j;
        }

        for (List<Integer> t : res){
            System.out.println(t);
        }

    }

    public static void dfs(TreeNode root) {
        if(root == null){
            return;
        }
        int row = map.get(root)[0];
        int col = map.get(root)[1];
        if(root.left != null){
            map.put(root.left, new int[]{row + 1, col - 1, root.left.val});
            dfs(root.left);
        }
        if(root.right != null){
            map.put(root.right, new int[]{row + 1, col + 1, root.right.val});
            dfs(root.right);
        }
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
