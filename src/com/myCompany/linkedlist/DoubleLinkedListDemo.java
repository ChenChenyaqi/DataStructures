package com.myCompany.linkedlist;

/**
 * @author chenyaqi
 * @date 2021/4/4 - 15:44
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 双向链表测试
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(3, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(2, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);

        doubleLinkedList.update(new HeroNode2(4,"小林","小豹子"));
        doubleLinkedList.deleteById(4);

        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    // 先初始化一个头节点,头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 添加节点到双向项链表
    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 第二种方式添加英雄，根据排名将英雄插入到指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode2 heroNode){
        // 头节点不能动，需要一个辅助指针（变量）来找到添加的位置
        HeroNode2 temp = head;
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
            if (temp.next !=null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            temp.next.pre = temp;
        } else{
            // 不可以添加
            System.out.println("插入失败，"+heroNode+"已存在");
        }
    }

    // 修改节点的信息，根据no编号来修改，no不能更改
    public void update(HeroNode2 newHeroNode){
        // 判断是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode2 temp = head.next;
        // 判断是否找到
        boolean flag = false;
        while(true){
            if (temp == null){
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

    // 删除双向链表中的一个节点
    public void deleteById(int no){
        // 判断是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            // 删除temp节点
            temp.pre.next = temp.next;
            // 防止删除最后一个节点时出现空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
            System.out.println("删除成功");
        } else {
            System.out.println("要删除的节点不存在：" + no);
        }
    }

    // 显示双向链表[遍历]
    public void list() {
        // 先判断是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 因为头节点不能动，所以需要一个临时变量来辅助遍历
        HeroNode2 temp = head.next;
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

// 定义一个HeroNode2，每个HeroNode对象就是一个节点(链表)
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    // 指向下一个节点，默认为空
    public HeroNode2 next;
    // 指向上一个节点，默认为空
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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
