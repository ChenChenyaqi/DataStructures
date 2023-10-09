package com.myCompany.tree;

/**
 * @author chenyaqi
 * @date 2021/6/13 - 10:02
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode1 root = new HeroNode1(1, "tom");
        HeroNode1 heroNode2 = new HeroNode1(3, "jack");
        HeroNode1 heroNode3 = new HeroNode1(6, "smith");
        HeroNode1 heroNode4 = new HeroNode1(8, "mary");
        HeroNode1 heroNode5 = new HeroNode1(10, "king");
        HeroNode1 heroNode6 = new HeroNode1(14, "dim");

        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);
        // 测试
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        System.out.println("线索化之前");
        System.out.println(heroNode5.getLeft());
        System.out.println(heroNode5.getRight());
        threadedBinaryTree.threadedNodes(root);
        System.out.println("线索化之后");
        System.out.println(heroNode5.getLeft());
        System.out.println(heroNode5.getRight());
        System.out.println("使用线索化的方式遍历");
        threadedBinaryTree.threadedList();
    }
}

// 创建二叉树
// 定义一个二叉树 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode1 root;
    // 指向当前节点的前驱节点的指针
    private HeroNode1 pre = null;

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    // 对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode1 node) {
        if (node == null) {
            return;
        }
        // (一)先线索化左子树
        threadedNodes(node.getLeft());

        // (二)线索化当前
        // (1)先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        // (2)再处理后继节点
        if (pre != null && pre.getRight() == null) {
            //  让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // !!!!!!(3)每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        // (三)线索化右子树
        threadedNodes(node.getRight());
    }

    // 遍历线索化二叉树
    public void threadedList(){
        HeroNode1 node = root;
        while (node != null){
            // 循环找到leftType = 1的节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点
            while(node.getRightType() == 1){
                // 获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
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
    public HeroNode1 preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 中序查找
    public HeroNode1 infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 后序查找
    public HeroNode1 postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return null;
    }

    // 删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }

}


// 创建节点
class HeroNode1 {
    private int no;
    private String name;
    // 默认null
    private HeroNode1 left;
    private HeroNode1 right;

    // 若leftType = 0表示指向的是 左子树，若 leftType = 1 则表示指向 前驱节点
    // 若rightType = 0表示指向的是 右子树，若 rightType = 1 则表示指向 后继节点
    private int leftType;
    private int rightType;

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历
    public void preOrder(HeroNode1 node) {
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
    public HeroNode1 preOrderSearch(int no) {
        System.out.println("进入前序查找");
        // 先比较当前节点
        if (this.no == no) {
            return this;
        }
        HeroNode1 resNode = null;
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
    public HeroNode1 infixOrderSearch(int no) {
        HeroNode1 resNode = null;
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
    public HeroNode1 postOrderSearch(int no) {
        HeroNode1 resNode = null;
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
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

