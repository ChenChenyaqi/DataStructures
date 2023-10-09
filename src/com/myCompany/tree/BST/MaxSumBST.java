package com.myCompany.tree.BST;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class MaxSumBST {

    private int maxSum;

    public static void main(String[] args) {

    }

    /*
    traverse(root) 返回一个大小为 4 的 int 数组，我们暂且称它为 res，其中：
    res[0] 记录以 root 为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST；
    res[1] 记录以 root 为根的二叉树所有节点中的最小值；
    res[2] 记录以 root 为根的二叉树所有节点中的最大值；
    res[3] 记录以 root 为根的二叉树所有节点值之和。
     */
    private int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[]{
                    1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0
            };
        }

        // 递归计算左右子树
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        // 后序遍历位置
        int[] res = new int[4];
        // 判断以 root 为根的二叉树是不是 BST
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {
            // 以 root 为根的二叉树是 BST
            res[0] = 1;
            // 计算以 root 为根的这棵 BST 的最小值
            res[1] = Math.min(left[1], root.val);
            // 计算以 root 为根的这棵 BST 的最大值
            res[2] = Math.max(right[2], root.val);
            // 计算以 root 为根的这棵 BST 所有节点之和
            res[3] = left[3] + right[3] + root.val;
            // 更新全局变量
            maxSum = Math.max(maxSum, res[3]);
        } else {
            // 以 root 为根的二叉树不是 BST
            res[0] = 0;
            // 其他的值都没必要计算了，因为用不到
        }

        return res;
    }
}
