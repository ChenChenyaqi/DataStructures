package com.intermediateClass.lesson6;

import java.util.TreeMap;

/**
 * 树形目录
 * 给你一个字符串数组arr
 * arr = {"b\\cst", "d\\", "a\\d\\e", "a\\b\\c"};
 * 按照这样的方式打印出来
 * a
 *   b
 *     c
 *   d
 *     e
 *  b
 *    cst
 *  d
 *  同一级按字母顺序排序
 *
 * 前缀树
 */
public class TreeDirectory {

    public static void main(String[] args) {
        String[] arr = new String[]{"b\\cst", "d\\", "a\\d\\e", "a\\b\\c"};
        print(arr);
    }

    public static class Node{
        public String name;
        // 下级有哪些路
        public TreeMap<String, Node> nextMap;

        public Node(String name){
            this.name = name;
            nextMap = new TreeMap<>();
        }
    }

    public static void print(String[] folderPaths){
        if(folderPaths == null || folderPaths.length == 0){
            return;
        }
        // 根据字符串，把前缀树建立好，头节点为head
        Node head = generateTrieTree(folderPaths);
        // 打印
        printProcess(head, 0);
    }

    // 生成前缀树
    public static Node generateTrieTree(String[] folderPaths){
        Node head = new Node("");
        for (String foldPath : folderPaths){
            // 根据 '\' 进行分割
            String[] paths = foldPath.split("\\\\");
            Node cur = head;
            for (String path : paths) {
                if (!cur.nextMap.containsKey(path)) {
                    cur.nextMap.put(path, new Node(path));
                }
                cur = cur.nextMap.get(path);
            }
        }
        return head;
    }

    // level当前层
    public static void printProcess(Node node, int level){
        if(level != 0){
            // 2 * (level - 1)
            System.out.println(get2nSpace(level) + node.name);
        }
        for (Node next : node.nextMap.values()){
            printProcess(next, level + 1);
        }
    }

    // 返回多少个空格
    private static String get2nSpace(int level) {
        StringBuilder space = new StringBuilder();
        for (int i = 1; i < level; i++) {
            space.append("  ");
        }
        return space.toString();
    }
}
