package com.BasedAscension.treeDp;

/**
 * 二叉树的两个节点的最大距离
 *
 * 分情况：  根据可能性分类
 * 1、root节点不参与
 *      左树里存在最远距离
 *      右树里的最大距离
 * 2、root节点参与
 *      左树高度 + 1 + 右树高度
 */
public class MaxDistanceInTree {

    static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int v){
            value = v;
        }
    }

    public static class ReturnData{
        public int maxDistance;
        public int height;

        public ReturnData(int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }

    public static int getMaxDistance(Node head){
        return process(head).maxDistance;
    }

    // 返回 以 x 为头的整棵树，两个信息
    public static ReturnData process(Node x){
        if(x == null){
            return new ReturnData(0,0);
        }
        ReturnData leftInfo = process(x.left);
        ReturnData rightInfo = process(x.right);

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + 1 + rightInfo.height;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new ReturnData(maxDistance, height);
    }

}
