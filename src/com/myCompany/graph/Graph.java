package com.myCompany.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author chenyaqi
 * @date 2021/5/23 - 18:47
 */
public class Graph {
    // 存储顶点集合
    private ArrayList<String> vertexList;
    // 存储图对应的邻接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 结点个数
        int n = 10;
        // 顶点
//        String[] vertexValue = {"1","2","3","4","5","6","7","8"};
        String[] vertexValue = {"1","25","24","23","21","9","15","13","12","27"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环添加顶点
        for (String value : vertexValue){
            graph.insertVertex(value);
        }
        // 添加边
        // A-B A-C B-C B-D B-E
        // 1-2 1-3 2-4 2-5 4-8 5-8 3-6 3-7 6-7
        // 1-25 25-24 24-23 23-21 21-9 21-27 9-15 15-13 13-12
        graph.insertEdge(0,1,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,1);
        graph.insertEdge(3,4,1);
        graph.insertEdge(4,5,1);
        graph.insertEdge(5,6,1);
        graph.insertEdge(6,7,1);
        graph.insertEdge(7,8,1);
        graph.insertEdge(4,9,1);
        // 显示邻接矩阵
        graph.showGraph();

        // dfs遍历
        System.out.println("深度遍历");
        graph.dfs(n);

        // bfs遍历
        System.out.println("\n广度遍历");
        graph.bfs(n);

    }

    public Graph(int n){
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    // 深度优先遍历算法
    // i 第一次就是0
    private void dfs(boolean[] isVisited, int i){
        // 首先我们访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        // 将该结点设置为已经访问过
        isVisited[i] = true;
        // 查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        // 说明有邻接结点
        while (w != -1){
            // 没有被访问过
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            // 如果被访问过
            if (isVisited[w]){
                w = getNextNeighbor(i, w);
            }
        }
    }

    // 对dfs进行一个重载，遍历我们所有的结点，并进行dfs
    public void dfs(int n){
        isVisited = new boolean[n];
        // 遍历所有的节点，进行dfs【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    // 对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i){
        // 队列头结点的下标
        int u;
        // 邻接结点w
        int w;
        // 队列, 记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问结点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        // 标记为已访问
        isVisited[i] = true;
        // 将结点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到第一个邻接点的下标w
            w = getFirstNeighbor(u);
            // 找到
            while (w != -1){
                // 没有访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    // 标记访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                } else {
                    // 访问过，以u为前驱结点，找w后面的下一个邻接点
                    // 体现出广度优先的算法
                    w = getNextNeighbor(u, w);
                }
            }
        }

    }

    // 遍历所有结点，进行广度优先搜索
    public void bfs(int n){
        isVisited = new boolean[n];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }


    // 得到第一个邻接结点的下标w , 若存在则返回对应的下标，不存在返回-1
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++){
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    // 返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    // 返回边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }

    // 返回结点i(下标)对应的数据 0 -> "A"  1 -> "B"  2 -> "C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWight(int v1, int v2){
        return edges[v1][v2];
    }

    /**
     * 插入结点
     * @param vertex 结点名称
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 第一个顶点的下标  A - 0,  B - 1
     * @param v2 第二个顶点的下标
     * @param weight 权重
     */
    public void insertEdge(int v1,int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}

