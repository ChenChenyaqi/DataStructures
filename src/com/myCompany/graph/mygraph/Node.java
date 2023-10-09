package com.myCompany.graph.mygraph;

import java.util.ArrayList;

/**
 * 点结构
 * @author Chen Yaqi
 * @version 1.0
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
