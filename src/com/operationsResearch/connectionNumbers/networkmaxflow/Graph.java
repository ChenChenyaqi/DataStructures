package com.operationsResearch.connectionNumbers.networkmaxflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public List<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
    }
}
