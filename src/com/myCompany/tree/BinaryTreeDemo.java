package com.myCompany.tree;

/**
 * @author chenyaqi
 * @date 2021/6/12 - 20:29
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建英雄节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        // 先手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        // 遍历测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
        // 查找测试
        System.out.println("前序查找");
        System.out.println(binaryTree.preOrderSearch(2));   // 4
        System.out.println(binaryTree.infixOrderSearch(2));  // 3
        System.out.println(binaryTree.postOrderSearch(2));   // 2
        // 删除测试
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();


    }
}

// 定义一个二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder(root);
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }

}

// 先创建HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    // 默认null
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历
    public void preOrder(HeroNode node) {
        // 先输出父节点
        System.out.println(node);
        // 递归向左子树前序遍历
        if (node.left != null) {
            preOrder(node.left);
        }
        // 递归向右子树前序遍历
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 先递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        // 先比较当前节点
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return null;
    }

    // 删除节点
    public void delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
