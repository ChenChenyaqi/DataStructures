package com.operationsResearch.connectionNumbers;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public ArrayList<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new ArrayList<Edge>();
    }
}
