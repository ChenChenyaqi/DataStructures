package com.myCompany.tenAlgorithm;

import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/7/21 - 8:46
 */
public class GreedyAlgorithm {
    // 贪心算法
    // 案例：五个广播站，分别可以覆盖的城市如下：
    /*
    * K1 : "北京","上海","天津";
    * K2 : "广州","北京","深圳";
    * K3 : "成都","上海","杭州";
    * K4 : "上海","天津";
    * K5 : "杭州","大连";
    * 要求在这5个广播站选出广播站数量最少且覆盖所有区域的组合
    * */
    public static void main(String[] args) {
        // 创建所有广播站
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashSet<String> hashSet4 = new HashSet<>();
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        hashSet4.add("上海");
        hashSet4.add("天津");

        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);
        // 创建所有城市
        HashSet<String> allCities = new HashSet<>();
        for (Map.Entry<String,HashSet<String>> entry : broadcasts.entrySet()){
            allCities.addAll(entry.getValue());
        }
        // 使用贪心算法，求出最优解
        List<String> res = greedyAlgorithm(broadcasts, allCities);
        System.out.println(res);
    }

    /**
     * 贪心算法求最优组合
     * @param broadcasts 所有的广播站
     * @param allCities 所有的城市
     * @return 最优选择组合
     */
    private static List<String> greedyAlgorithm(HashMap<String, HashSet<String>> broadcasts, HashSet<String> allCities) {
        // 选择组合结果集
        List<String> select = new ArrayList<>();
        // 每次循环中找到的最大覆盖未覆盖城市的数目的广播站
        String maxKey = null;
        // 临时变量,用于求maxKey的辅助变量
        HashSet<String> temp = new HashSet<>();
        // 当allCities里没有被覆盖完(每次覆盖一个城市，便把该城市从allCities中剔除)
        while(allCities.size() != 0){
            // 遍历所有广播站
            for (String key : broadcasts.keySet()){
                // 依次把每个广播站覆盖的城市添加进temp里
                temp.addAll(broadcasts.get(key));
                // 求出temp和allCities的交集
                temp.retainAll(allCities);
                // 如果还存在交集，即还有未覆盖的城市  并且  temp的数量比maxKey的多，则交换maxKey，即寻找最大的key
                if (temp.size() > 0 && (maxKey == null || temp.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
                // 清除temp里的数据
                temp.clear();
            }
            if (maxKey != null){
                // 添加此次寻找到的maxKey
                select.add(maxKey);
                // 把已经覆盖过的城市从allCities里剔除
                allCities.removeAll(broadcasts.get(maxKey));
            }
            // 把maxKey置空
            maxKey = null;
        }
        return select;
    }
}
