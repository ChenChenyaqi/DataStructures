package com.operationsResearch.connectionNumbers.tableMethod;

import java.util.ArrayList;

public class Node {
    // 这个点的值
    public TableDate value;
    // 度
    public Integer du;
    // 此点 发散出去的边连接的点
    public ArrayList<Node> nexts;
    // 此点 发散出去的边
    public ArrayList<Edge> edges;

    public Node(TableDate value) {
        this.value = value;
        this.du = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
