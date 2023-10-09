package com.operationsResearch.connectionNumbers.tableMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    public HashMap<TableDate, Node> nodes;
    public List<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
    }
}
