package com.operationsResearch.connectionNumbers;

import java.util.HashMap;

public class GuardPoint {
    public HashMap<Integer, Edge> edges;
    public Node master;

    public GuardPoint(Node master){
        this.master = master;
        this.edges = new HashMap<>();
    }
}
