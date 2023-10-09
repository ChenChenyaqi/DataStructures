package com.operationsResearch.connectionNumbers;

public class Node {
    public Integer value;
    public Coordinate coordinate;
    public GuardPoint[] guardPoint;

    public Node(int value, int x, int y) {
        this.value = value;
        this.coordinate = new Coordinate(x, y);
        this.guardPoint = new GuardPoint[4];
    }
}
