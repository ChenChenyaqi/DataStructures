package com.BasedAscension.treeDp;

/**
 * Morris遍历
 * <p>
 * 一种遍历二叉树的方式，并且时间复杂度O(N)，空间复杂度O(1)
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
 * <p>
 * Morris遍历细节
 * <p>
 * 假设来到当前节点cur，开始时cur 来到头节点位置
 * 1) 如果cur没有左孩子，cur向右移动（cur = cur.right）
 * 2) 如果cur有左孩子，找到左子树上最右的节点 mostRight:
 * a. 如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)
 * b. 如果mostRight的右指针指向cur，让其指向null，然后cur向右移动（cur = cur.right）
 * 3) cur为空时遍历停止
 */
public class Morris {

    public static void main(String[] args) {
        Node head = new Node();
        head.value = 1;
        head.left = new Node();
        head.left.value = 2;
        head.right = new Node();
        head.right.value = 3;
        head.left.left = new Node();
        head.left.left.value = 4;
        head.left.right = new Node();
        head.left.right.value = 5;
        /*
         *       1
         *     2   3
         *  4    5
         * */

        postMorris(head);
        isBST(head);
        System.out.println();

    }

    static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) { // 找到最右节点
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次来到这个节点
                    // 先序遍历 第一次来直接打印
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                // 先序遍历
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    // 中序遍历
    public static void inMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) { // 找到最右节点
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次来到这个节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 中序遍历
            System.out.println(cur.value);
            cur = cur.right;
        }
    }


    // 后序遍历
    public static void postMorris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) { // 找到最右节点
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次来到这个节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    // 逆序打印左树的右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        // 单独打印整个树的右边界
        printEdge(head);
        System.out.println();
    }

    // 以 x 为头的树，逆序打印这棵树的右边界
    public static void printEdge(Node x) {
        Node tail = reverseEdge(x);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 逆序树右边界
    private static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }


    // morris应用，判断是不是搜索二叉树？
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer preValue = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (preValue == null) {
                preValue = cur.value;
                cur = cur.right;
            }
            if (cur.value <= preValue) {
                return false;
            }
            preValue = cur.value;
            cur = cur.right;
        }
        return true;
    }
}
