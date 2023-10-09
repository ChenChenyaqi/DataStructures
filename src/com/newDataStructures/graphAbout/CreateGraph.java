package com.newDataStructures.graphAbout;

import com.newDataStructures.graphAbout.graphStructure.Edge;
import com.newDataStructures.graphAbout.graphStructure.Graph;
import com.newDataStructures.graphAbout.graphStructure.Node;

/**
 * 接口函数，把 某种 图的表示方式 转为定义好的图的数据结构
 */
public class CreateGraph {

    /**
     * @param matrix matrix[0][0] from, matrix[0][1] to, matrix[0][2] weight
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[0][0];
            int to = matrix[0][1];
            int weight = matrix[0][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}

