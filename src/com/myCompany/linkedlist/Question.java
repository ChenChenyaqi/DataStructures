package com.myCompany.linkedlist;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class Question {
    public static void main(String[] args) {
        // Q1、判断一个链表是否为回文结构
        // 给定一个单链表的头结点head，请判断该链表是否是回文结构（快慢指针法）
        ListNode head = new ListNode(1);
        System.out.println(isPalindrome(head));
        head.next = new ListNode(2);
        System.out.println(isPalindrome(head));
        head.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));


        // Q2、把一个链表按照某个值分为小于，大于，等于区域（6个变量法）
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next = new ListNode(9);

//        ListNode splitList = splitList(head, 4);
//        showList(splitList);

        // Q3、判断两个链表是否相交，并返回相交的第一个节点（链表可能存在环形结构）
        showList(head);
        head.next.next.next.next.next.next.next.next = head.next.next.next;
        System.out.println(existLoop(head));

    }

    // 返回两个有环结构链表的相交点
    public static ListNode bothLoopList(ListNode headA, ListNode loopA, ListNode headB, ListNode loopB) {
        if (loopA == loopB) {
            // 调用求没有环结构的方法，loopA为它们的终止节点
            return noLoopList(headA, headB, loopA);
        } else {
            // 让loopA继续走，走一圈之后若没有遇到loopB，说明俩链表独立
            ListNode temp = loopA.next;
            while (temp != loopA) {
                if (temp == loopB) {
                    return loopA;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    // 主方法
    public static ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if (existLoop(head1) == null && existLoop(head2) == null) {
            // 都没有环形结构
            return noLoopList(head1, head2, null);
        } else if (existLoop(head1) != null && existLoop(head2) != null) {
            // 都有环形结构
            return bothLoopList(head1, existLoop(head1), head2, existLoop(head2));
        } else {
            // 一有一没有，一定不相交
            return null;
        }
    }


    // 返回两个没有环结构链表的相交点,loop默认为null
    public static ListNode noLoopList(ListNode head1, ListNode head2, ListNode loop) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        // 两个链表长度的差值
        int n = 0;
        // 先遍历head1
        while (temp1.next != loop) {
            n++;
            temp1 = temp1.next;
        }
        // 再遍历head2
        while (temp2.next != loop) {
            n--;
            temp2 = temp2.next;
        }
        // 若它们的末尾不相同,则没有相交
        if (temp1 != temp2) {
            return null;
        }
        // temp1指向长的那个链表头
        temp1 = n > 0 ? head1 : head2;
        // temp2指向另一个头
        temp2 = temp1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            temp1 = temp1.next;
        }
        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }


    // 判断一个链表中是否存在环形结构，存在返回环形结构入口节点，不存在返回null
    // 快慢指针法
    public static ListNode existLoop(ListNode head) {
        // 如果链表的长度小于3，则构不成环形结构
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 快指针从第三个开始
        ListNode quick = head.next.next;
        // 慢指针从第二个开始
        ListNode slow = head.next;
        while (quick != null && quick.next != null && quick != slow) {
            quick = quick.next.next;
            slow = slow.next;
        }
        // 说明没有Loop
        if (quick == null || quick.next == null) {
            return null;
        }
        // 说明有环形结构
        quick = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        // 循环结束时，就是环形结构的入口节点
        return quick;
    }

    // 把链表按照某个值分为小于，大于和等于区域
    public static ListNode splitList(ListNode head, int target) {
        if (head == null || head.next == null) {
            return head;
        }
        // 小于区域
        ListNode lessLeft = null;
        ListNode lessRight = null;
        // 等于区域
        ListNode equalLeft = null;
        ListNode equalRight = null;
        // 大于区域
        ListNode lagerLeft = null;
        ListNode lagerRight = null;
        // 遍历指针
        ListNode temp = head;
        // 分割小于等于大于区域
        while (temp != null) {
            if (temp.val < target) {
                if (lessLeft == null) {
                    lessLeft = temp;
                } else {
                    lessRight.next = temp;
                }
                lessRight = temp;
            } else if (temp.val == target) {
                if (equalLeft == null) {
                    equalLeft = temp;
                } else {
                    equalRight.next = temp;
                }
                equalRight = temp;
            } else {
                if (lagerLeft == null) {
                    lagerLeft = temp;
                } else {
                    lagerRight.next = temp;
                }
                lagerRight = temp;
            }
            temp = temp.next;
        }
        // 连接各个区域
        if (lessRight != null) {
            lessRight.next = equalLeft;
            equalRight = equalRight == null ? lessRight : equalRight;
        }

        if (equalRight != null) {
            equalRight.next = lagerLeft;
        }
        return lessLeft != null ? lessLeft : (equalLeft != null ? equalLeft : lagerLeft);
    }

    // 判断是否是回文结构
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        ListNode slowPointer = head;
        ListNode quickPointer = head;
        // 当快指针为空，说明这是偶数个；当快指针的下一个为空，说明这是奇数个
        while (quickPointer != null && quickPointer.next != null) {
            quickPointer = quickPointer.next.next;
            slowPointer = slowPointer.next;
        }
        // 右半边逆序
        quickPointer = slowPointer.next;
        slowPointer.next = null;
        ListNode temp = null;
        while (quickPointer != null) {
            temp = quickPointer.next;
            quickPointer.next = slowPointer;
            // 慢指针右移
            slowPointer = quickPointer;
            // 快指针右移
            quickPointer = temp;
        }
        // 慢指针从末尾向中间走，快指针从头节点向中间走
        temp = slowPointer;
        quickPointer = head;
        // 记录是否回文
        boolean flag = true;
        while (slowPointer != null && quickPointer != null) {
            if (slowPointer.val != quickPointer.val) {
                flag = false;
                break;
            }
            slowPointer = slowPointer.next;
            quickPointer = quickPointer.next;
        }
        // 恢复右半边
        slowPointer = temp.next;
        temp.next = null;
        while (slowPointer != null) {
            quickPointer = slowPointer.next;
            slowPointer.next = temp;
            temp = slowPointer;
            slowPointer = quickPointer;
        }
        return flag;
    }

    // 遍历输出链表
    public static void showList(ListNode head) {
        if (head == null) {
            System.out.println("链表为空！");
        }
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }
}
