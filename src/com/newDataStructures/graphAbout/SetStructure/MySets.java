package com.newDataStructures.graphAbout.SetStructure;


import com.newDataStructures.graphAbout.graphStructure.Node;

import java.util.*;

/**
 * Kruskal算法 求 最小生成树所需 的 集合结构
 */
public class MySets {
   public Map<Node, Set<Node>> setMap = new HashMap<>();

   public MySets(ArrayList<Node> nodes){
       for(Node cur : nodes){
           HashSet<Node> set = new HashSet<>();
           set.add(cur);
           setMap.put(cur, set);
       }
   }

    public boolean isSameSet(Node from, Node to){
        Set<Node> fromSet = setMap.get(from);
        Set<Node> toSet = setMap.get(to);
        return fromSet == toSet;
    }

    public void union(Node from, Node to){
        Set<Node> fromSet = setMap.get(from);
        Set<Node> toSet = setMap.get(to);

        for(Node toNode : toSet){
            fromSet.add(toNode);
            setMap.put(toNode, fromSet);
        }
    }
}
