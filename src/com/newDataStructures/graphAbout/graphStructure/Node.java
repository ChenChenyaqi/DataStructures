package com.newDataStructures.graphAbout.graphStructure;

import java.util.ArrayList;

public class Node {
    // 这个点的值
    public Integer value;
    // 入度
    // 有多少个点 发散出来的边 是直接指向这个点的
    public Integer in;
    // 出度
    // 此点 发散出多少个边
    public Integer out;
    // 此点 发散出去的边连接的点
    public ArrayList<Node> nexts;
    // 此点 发散出去的边
    public ArrayList<Edge> edges;

    public Node(Integer value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
