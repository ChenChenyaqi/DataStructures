package com.operationsResearch.connectionNumbers.networkmaxflow;

import java.util.ArrayList;

public class Node {
    // 这个点的值
    public Integer value;
    // 此点 发散出去的边连接的点
    public ArrayList<Node> nexts;
    // 此点 发散出去的边
    public ArrayList<Edge> edges;
    // e = (u, v)  p(v) = +u / -u
    public Integer p;
    // theta 调整量
    public Integer theta;

    public Node(Integer value) {
        this.value = value;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
