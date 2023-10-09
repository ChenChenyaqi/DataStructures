package com.myCompany.linkedlist;

/**
 * @author chenyaqi
 * @date 2021/4/4 - 18:25
 */
public class Josephus {
    public static void main(String[] args) {
        // 测试1:
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.countBoy(1,3,41);





        // 有5个小朋友坐在地上围成一圈，他们有自己的编号：1, 2, 3, 4, 5.
        // 现在从1号小朋友开始数，每数2个小朋友，这第二个小朋友就要出圈(按从1到2的方向数)
        // 然后从下一个开始，继续数，数到第二个小朋友时，出圈
        // 依次类推，请问，最后小朋友出圈的顺序是多少？













    }
}

class CircleSingleLinkedList{
    // 第一个小孩
    private Boy first = null;
    // 添加小孩
    public void addBoy(int num){
        // 先校验num的值
        if (num < 1){
            System.out.println("输入的人数不对");
            return;
        }
        // 辅助变量
        Boy curBoy = null;
        // 添加
        for (int i = 1; i <= num; i++) {
            // 新增节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
    }
    // 遍历当前的环形列表
    public void showBoy(){
        // 先判断是否为空
        if (first == null){
            System.out.println("链表为空！");
            return;
        }
        // 因为first不能动，所以仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.println(curBoy);
            if (curBoy.getNext() == first){
                // 说明到达最后
                break;
            }
            // 后移
            curBoy = curBoy.getNext();
        }

    }
    /**
     * 根据用户的输入，计算小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        // 校验参数合理性
        if (startNo < 1 || startNo > nums){
            System.out.println("输入的参数不合理！");
            return;
        }
        // 创建小孩
        addBoy(nums);
        // 创建辅助的指针
        Boy helper = first;
        // 让helper指向最后一个节点
        while (helper.getNext() != first){
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper移动 startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 数countNum次后，一个小孩出圈
        while (true){
            // 当仅剩一个小孩时
            if (helper == first){
                System.out.println(first);
                break;
            }
            // 让first和helper同时移动countNum次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // first所指的小孩出圈
            System.out.println(first);
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Boy{
    // 编号
    private int no;
    // 指向下一个节点，默认为null
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}

