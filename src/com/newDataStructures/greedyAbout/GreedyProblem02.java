package com.newDataStructures.greedyAbout;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一组 字符串， 请把 它们组合在一起，使得整体字典序最小
 */
public class GreedyProblem02 {

    public static class StrComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String problem02(String[] strings){
        if(strings == null || strings.length == 0){
            return "";
        }
        Arrays.sort(strings, new StrComparator());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            res.append(strings[i]);
        }
        return res.toString();
    }
}
