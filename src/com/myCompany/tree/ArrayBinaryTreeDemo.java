package com.myCompany.tree;

/**
 * @author chenyaqi
 * @date 2021/6/13 - 9:18
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序");
        arrayBinaryTree.preOrder();
        System.out.println("中序");
        arrayBinaryTree.infixOrder(0);
        System.out.println("后序");
        arrayBinaryTree.postOrder(0);


    }
}

// 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree {
    // 存储数据节点的数组
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder(){
        this.preOrder(0);
    }

    // 顺序存储二叉树的前序遍历，index为数组下标
    public void preOrder(int index) {
        // 若数组为空，或长度为0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(array[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    // 顺序存储二叉树的中序遍历
    public void infixOrder(int index){
        // 若数组为空，或长度为0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            infixOrder(index * 2 + 1);
        }
        // 输出当前这个元素
        System.out.println(array[index]);
        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            infixOrder(index * 2 + 2);
        }
    }

    // 顺序存储二叉树的后序遍历
    public void postOrder(int index){
        // 若数组为空，或长度为0
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            postOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < array.length) {
            postOrder(index * 2 + 2);
        }
        // 输出当前这个元素
        System.out.println(array[index]);
    }
}