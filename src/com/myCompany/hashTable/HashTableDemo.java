package com.myCompany.hashTable;

import java.util.Scanner;

/**
 * @author chenyaqi
 * @date 2021/6/12 - 15:27
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTable hashTable = new HashTable(7);

        // 编写一个菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("delete：删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "l":
                    hashTable.list();
                    break;
                case "f":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "e":
                    System.out.println("退出系统！");
                    scanner.close();
                    System.exit(0);
                    break;
                case "d":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.deleteEmpById(id);
                    break;
                default:
                    break;
            }
        }
    }
}

// 创建HashTable 管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    // 表示共有多少条链表
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        // 不要忘了分别初始化每个EmpLinkedList
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员,若已存在则更新name值
    public void add(Emp emp) {
        // 根据id，得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    // 遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    // 根据id查找雇员
    public void findEmpById(int id) {
        // 使用散列函数确定在哪条链表
        int emLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[emLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + (emLinkedListNo + 1) + "链表中找到改雇员" + emp);
        } else {
            System.out.println("在哈希表中没有找到该雇员！");
        }
    }

    // 根据id删除雇员
    public void deleteEmpById(int id) {
        int emLinkedListNo = hashFun(id);
        empLinkedListArray[emLinkedListNo].deleteEmpById(id);
    }

    // 散列函数
    public int hashFun(int id) {
        return id % size;
    }
}

// 创建EmpLinkedList 表示链表
class EmpLinkedList {
    // 头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    // 默认为空
    private Emp head;

    /**
     * 添加员工到链表中,若已存在则更新name值
     *
     * @param emp 员工
     */
    public void add(Emp emp) {
        // 如果是第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        // 若不是第一个员工
        // 先查找此id是否已经被使用
        Emp findEmp = findEmpById(emp.id);
        if (findEmp != null) {
            // 更新数据
            findEmp.name = emp.name;
        } else {
            Emp newHead = new Emp(0, "0");
            newHead.next = head;
            Emp pre = newHead;
            Emp cur = newHead.next;
            // 是否已经插入
            boolean isInserted = false;
            while (cur != null) {
                // 若当前位置的id大于待插入的id
                if (cur.id > emp.id) {
                    pre.next = emp;
                    emp.next = cur;
                    isInserted = true;
                    break;
                }
                pre = pre.next;
                cur = cur.next;
            }
            // 若没有插入，则把emp添加到末尾
            if (!isInserted){
                pre.next = emp;
            }
            head = newHead.next;
        }
    }

    /**
     * 遍历链表的员工信息
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.println("第" + (no + 1) + "链表的信息为：");
        Emp temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 根据id查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        Emp temp = head;
        while (temp != null) {
            if (temp.id == id) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    // 根据id删除雇员
    public void deleteEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空!");
            return;
        }
        Emp newHead = new Emp(0, "0");
        newHead.next = head;
        Emp pre = newHead;
        Emp cur = newHead.next;
        while (cur != null) {
            if (cur.id == id) {
                pre.next = cur.next;
                System.out.println("删除成功！");
                break;
            }
            pre = pre.next;
            cur = cur.next;
        }
        head = newHead.next;
    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    // 默认为空
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

