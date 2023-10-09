package com.operationsResearch.connectionNumbers.minPath;

import java.util.ArrayList;

public class Node {
    // 这个点的值
    public Integer value;
    // 此点 发散出去的边连接的点
    public ArrayList<Node> nexts;
    // 此点 发散出去的边
    public ArrayList<Edge> edges;
    // 此点到起点的最短距离
    public Integer sumMin;
    // 此点前一个点
    public Node preNode;

    public Node(Integer value) {
        this.value = value;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
