package com.myCompany.linkedlist;

import java.util.Stack;

/**
 * @author chenyaqi
 * @date 2021/4/3 - 12:54
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        /*
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero2);
        singleListedList.addByOrder(hero3);
        // 修改前
        System.out.println("修改前：");
        singleListedList.list();
        // 修改
        HeroNode newHeroNode = new HeroNode(3,"小误","小星");
        singleListedList.update(newHeroNode);
        // 显示
        System.out.println("修改后：");
        singleListedList.list();
        // 删除
        singleListedList.deleteById(1);
        singleListedList.deleteById(2);
        singleListedList.deleteById(3);
        singleListedList.deleteById(4);
        singleListedList.list();*/

        // 题目1测试：
        /*HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero2);
        singleListedList.addByOrder(hero3);
        System.out.println(getLength(singleListedList.getHead()));*/

        // 题目2测试：
        /*HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero2);
        singleListedList.addByOrder(hero3);

        System.out.println(getLastIndexNode(singleListedList.getHead(),2));*/

        // 题目3测试：
        /*HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero2);
        singleListedList.addByOrder(hero3);

        printReverseList2(singleListedList.getHead());*/

        // 题目5测试：
        /*HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero3);

        SingleListedList singleListedList1 = new SingleListedList();
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero5 = new HeroNode(5, "卢俊义2", "玉麒麟2");
        HeroNode hero7 = new HeroNode(7, "卢俊义3", "玉麒麟3");
        singleListedList1.addByOrder(hero2);
        singleListedList1.addByOrder(hero5);
        singleListedList1.addByOrder(hero7);

        HeroNode heroNode = combineTowList(singleListedList.getHead(), singleListedList1.getHead());
        HeroNode temp = heroNode.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }*/

        /*HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleListedList singleListedList = new SingleListedList();
        // 加入
        singleListedList.addByOrder(hero1);
        singleListedList.addByOrder(hero4);
        singleListedList.addByOrder(hero3);

        HeroNode newHand = new HeroNode(0,"","");
        newHand.next = new HeroNode(0,"","");
        HeroNode temp = newHand.next;
        HeroNode hand = singleListedList.getHead();
        temp.next  = hand.next;
        temp = newHand.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }*/
    }

    public static void test(HeroNode head){
        HeroNode temp = head.next;
        temp.next.next = null;
    }

    // 题目1：获取到单链表的节点个数（如果是带头节点的链表，则不统计头节点）
    public static int getLength(HeroNode head){
        int length = 0;
        HeroNode temp = head;
        while(true){
            if (temp.next == null){
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 题目2：查找单链表中的倒数第k个节点（新浪面试题）
    public static HeroNode getLastIndexNode(HeroNode head, int index){
        // 若链表为空，返回null
        if (head.next == null){
            return null;
        }
        // 第一次遍历得到链表的长度（节点数）
        int size = getLength(head);
        // 先做一个index校验
        if (index <= 0 || index > size){
            return null;
        }
        // 定义一个辅助变量，for循环到倒数的index位置
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 题目3：单链表反转（腾讯面试题）
    public static void reverseList(HeroNode head){
        // 如果链表为空或只有一个节点，则不反转
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode newHead = new HeroNode(0,"","");
        while (temp != null){
            next = temp.next;
            temp.next = newHead.next;
            newHead.next = temp;
            temp = next;
        }
        head.next = newHead.next;
    }

    // 题目4：从尾到头打印单列表（百度面试题）
    // 方式一：先反转再打印（不建议）
    public static void printReverseList1(HeroNode head){
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        if (head.next.next == null){
            System.out.println(head.next);
            return;
        }

        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode newHead = new HeroNode(0,"","");
        while (temp != null){
            next = temp.next;
            temp.next = newHead.next;
            newHead.next = temp;
            temp = next;
        }
        head.next = newHead.next;
        temp = head.next;
        while (temp!=null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
    // 方式二：利用栈，逆序打印
    public static void printReverseList2(HeroNode head){
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            heroNodeStack.push(temp);
            temp = temp.next;
        }
        while (heroNodeStack.size() > 0){
            System.out.println(heroNodeStack.pop());
        }
    }

    // 题目5：合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode combineTowList(HeroNode head1, HeroNode head2){
        if (head1.next == null && head2.next == null){
            System.out.println("两个链表都为空");
            return null;
        }
        if (head1.next == null){
            return head2;
        }
        if (head2.next == null){
            return head1;
        }
        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        HeroNode newHead = new HeroNode(0,"","");
        HeroNode temp = newHead;

        while (temp1 != null && temp2 != null){
            if (temp1.no < temp2.no){
                temp.next = temp1;
                temp1 = temp1.next;
            } else{
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 == null){
            temp.next = temp2;
        }
        if (temp2 == null){
            temp.next = temp1;
        }
        return newHead;
    }
}

// 定义一个SingleLinkedList 管理我们的英雄 （链表）
class SingleListedList {
    // 先初始化一个头节点,头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单项链表
    // 思路：当不考虑编号顺序时
    // 1. 找的当前链表的最后节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode) {
        // 因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            } else {
                // 若没有找到最后，将temp后移
                temp = temp.next;
            }
        }
        // 当退出while循环，temp就指向了链表的最后
        // 将最后这个节点的next 指向新的节点
        temp.next = heroNode;
    }

    // 第二种方式添加英雄，根据排名将英雄插入到指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        // 头节点不能动，需要一个辅助指针（变量）来找到添加的位置
        // 因为单链表，所以需要找到位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        // 标记要添加的编号是否已经存在，默认为false
        boolean flag = false;

        while(true){
            if (temp.next == null){ // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){ // 位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no){ // 说明要插入的已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag的值
        if (!flag){
            // 可以添加
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else{
            // 不可以添加
            System.out.println("插入失败，"+heroNode+"已存在");
        }
    }

    // 修改节点的信息，根据no编号来修改，no不能更改
    public void update(HeroNode newHeroNode){
        // 判断是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode temp = head.next;
        // 判断是否找到
        boolean flag = false;
        while(true){
            if (temp.next == null){
                // 已经到达最后，不存在此节点
                break;
            }
            if (temp.no == newHeroNode.no){
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
            System.out.println("修改完成！");
        } else {
            System.out.println(newHeroNode + " 不存在！");
        }
    }

    // 删除节点
    public void deleteById(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            // 删除temp后一个节点
            temp.next = temp.next.next;
            System.out.println("删除成功");
        } else {
            System.out.println("要删除的节点不存在：" + no);
        }
    }

    // 显示链表[遍历]
    public void list() {
        // 先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 因为头节点不能动，所以需要一个临时变量来辅助遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息并temp后移
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

// 定义一个HeroNode，每个HeroNode对象就是一个节点(节点)
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    // 指向下一个节点
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
