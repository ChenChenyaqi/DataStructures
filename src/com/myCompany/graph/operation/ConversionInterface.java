package com.myCompany.graph.operation;

import com.myCompany.graph.mygraph.Edge;
import com.myCompany.graph.mygraph.Graph;
import com.myCompany.graph.mygraph.Node;

/**
 * 把一些其他结构表示的图，转换为我使用的图结构
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class ConversionInterface {

    /**
     * 把matrix表示法转化为Graph表示法
     * matrix[0][0] = weight 权重; matrix[0][1] = fromNode 从哪个节点来; matrix[0][2] = toNode 到哪个节点去;
     *
     * @param matrix matrix
     * @return 我的图
     */
    public static Graph conversion(int[][] matrix) {
        // 排除不合法的matrix
        if (matrix == null || matrix[0].length < 3) {
            return null;
        }
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            // 如果nodes里没有from
            if (!graph.nodes.containsKey(from)) {
                // 以from为编号，from为值创建Node，存入nodes里
                graph.nodes.put(from, new Node(from));
            }
            // 存to
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            // 边
            Edge edge = new Edge(weight, fromNode, toNode);
            // fromNode的nexts下一个节点
            fromNode.nexts.add(toNode);
            // 流出
            fromNode.out++;
            // 流入
            toNode.in++;
            // fromNode拥有的边
            fromNode.edges.add(edge);
            // graph的边
            graph.edges.add(edge);
        }
        return graph;
    }
}
