package com.myCompany.graph.mygraph;

/**
 * 边结构
 * @author Chen Yaqi
 * @version 1.0
 */
public class Edge {
    // 边的权重
    public int weight;
    // 这条边从哪来
    public Node from;
    // 这条边到哪去
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
