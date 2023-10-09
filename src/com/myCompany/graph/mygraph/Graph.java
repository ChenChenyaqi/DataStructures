package com.myCompany.graph.mygraph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 通用图结构
 * @author Chen Yaqi
 * @version 1.0
 */
public class Graph {
    // 存储点，key-点的编号，value-点
    public HashMap<Integer,Node> nodes;
    // 存储边
    public HashSet<Edge> edges;

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
