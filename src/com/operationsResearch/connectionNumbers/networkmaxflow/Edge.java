package com.operationsResearch.connectionNumbers.networkmaxflow;

public class Edge {
    // 容量
    public Integer c;
    // 流量
    public Integer f;
    // 起点
    public Node from;
    // 终点
    public Node to;

    public Edge(Integer c, Integer f, Node from, Node to){
        this.c = c;
        this.f = f;
        this.from = from;
        this.to = to;
    }

    public String toString(){
        return " edge: (" + from.value + ", " + to.value + ") ";
    }
}
