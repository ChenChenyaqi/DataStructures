package com.BasedAscension.treeDp;

import java.util.List;

/**
 * 聚会最大快乐值
 *
 * 一个公司，每个员工都有自己的上级，和 n个下级
 * boss作为根节点，可以给自己的下级发请柬，但是不能直接给下级的下级发请柬
 * 一个员工来参加聚会，那么他的下级就不能来了
 */
public class PartyHappyValue {

    public static class Employee{
        public int happy;
        public List<Employee> nexts;
    }

    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.laiMaxHappy, headInfo.buMaxHappy);
    }

    public static class Info{
        // 此员工来时的最大值
        public int laiMaxHappy;
        // 此员工不来时的最大值
        public int buMaxHappy;

        public Info(int lai, int bu){
            laiMaxHappy = lai;
            buMaxHappy = bu;
        }
    }

    public static Info process(Employee x){
        if(x.nexts.isEmpty()){
            return new Info(x.happy, 0);
        }
        int lai = x.happy;
        int bu = 0;
        for(Employee next : x.nexts){
            Info nextInfo = process(next);
            // 我来，则下面的不来
            lai += nextInfo.buMaxHappy;
            // 我不来，下面的 可以来 ，也可以不来
            bu += Math.max(nextInfo.laiMaxHappy, nextInfo.buMaxHappy);
        }
        return new Info(lai, bu);
    }
}
