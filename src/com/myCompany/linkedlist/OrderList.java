package com.myCompany.linkedlist;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class OrderList {
    private ListNode head;

    public static void main(String[] args) {
        // 初始化链表
        OrderList list = new OrderList(new ListNode(0));
        for (int i = 1; i < 1001; i++) {
            if (i == 500) {
                continue;
            }
            list.insertNode(i);
        }
        System.out.println("插入前：");
        list.showList();
        // 插入501到链表中
        list.insertNode(1001);

        System.out.println("插入后：");
        list.showList();

        list.deleteElementByIndex(1005);
        System.out.println("删除后：");
        list.showList();
    }

    public OrderList() {
    }

    public OrderList(ListNode head) {
        this.head = head;
    }

    /**
     * 向有序（从小到大）链表中插入一个数
     *
     * @param val  插入的值
     */
    public void insertNode(int val) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        // 前指针pre 和 当前指针cur
        ListNode pre = head;
        ListNode cur = head.next;
        // 插入比head值小到数
        if (pre.val > val) {
            head = new ListNode(val, head.next);
            return;
        }
        // 当链表只有一个头结点,且在head后插入
        if (cur == null && pre.val < val) {
            pre.next = new ListNode(val);
            return;
        }
        // 一般情况，在链表中插入值
        while (cur != null) {
            if (pre.val <= val && cur.val > val) {
                ListNode temp = new ListNode(val);
                pre.next = temp;
                temp.next = cur;
                return;
            }
            pre = pre.next;
            cur = cur.next;
        }
        // val插入到末尾的情况
        if (pre.val <= val) {
            pre.next = new ListNode(val);
        }
    }

    /**
     * 根据索引 index 删除链表中一个元素
     * @param index 删除索引
     * @return 删除成功返回 1，否则返回 -1
     */
    public int deleteElementByIndex( int index){
        if (head == null){
            System.out.println("链表为空");
            return -1;
        }
        // 删除头节点
        if (index == 0){
            head = head.next;
            return 1;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        int count = 1;
        while(cur != null){
            if (count == index){
                pre.next = cur.next;
                return 1;
            }
            pre = pre.next;
            cur = cur.next;
            count++;
        }
        return -1;
    }

    /**
     * 输出链表
     *
     */
    public void showList() {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        ListNode temp = head;
        int i = 1;
        while (temp != null) {
            System.out.print(temp.val + "->");
            if (i % 20 == 0) {
                System.out.println();
            }
            temp = temp.next;
            i++;
        }
        System.out.println();
    }
}
