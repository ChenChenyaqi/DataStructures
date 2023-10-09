package com.operationsResearch.connectionNumbers.minPath;

public class Edge {
    public Integer weight;
    // 起点
    public Node from;
    // 终点
    public Node to;

    public Edge(Integer weight, Node from, Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public String toString(){
        return " edge: (" + from.value + ", " + to.value + ") ";
    }
}
