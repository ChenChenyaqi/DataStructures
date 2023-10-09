package com.myCompany.math;

import java.util.Hashtable;

/**
 * @author chenyaqi
 * @date 2021/5/21 - 11:52
 */
public class MathB {
    public static Map[] maps;
    public static int[][] weatherCost;
    public static int wealth;
    public static int bagRemainCap;
    public static int day;
    public static Map temp;
    public static int waterCount;
    public static int foodCount;
    public static final int BASIC_EARNINGS = 1000;

    public static void main(String[] args) {
        // 初始化地图
        maps = initializeMap();
        // 初始化天气
        weatherCost = initializeWeather();
        // 初始资金
        wealth = 10000;
        // 背包剩余容量
        bagRemainCap = 1200;
        Map temp = maps[0];
        Hashtable<Integer, Boolean> used = new Hashtable<>();
        for (Map map : maps) {
            used.put(map.name, false);
        }
        dfs(temp, used);
        System.out.println("\n"+foodCount);
        System.out.println(used);

    }

    private static void dfs(Map temp, Hashtable<Integer, Boolean> used) {
        System.out.print(temp.name + " -> ");
        if (temp.next == null){
            return;
        }
        for (int i = 0; i < temp.next.length; i++) {
            if (!used.get(temp.name)) {
                used.replace(temp.name, true);
                foodCount++;
                dfs(temp.next[i], used);
                foodCount++;
                used.replace(temp.name, false);
            }
        }
    }


    /**
     * 判断背包是否已满
     *
     * @return true Or false
     */
    public static boolean isFull() {
        return bagRemainCap <= 0;
    }

    /**
     * 判断是否结束游戏
     *
     * @return true Or false
     */
    public static boolean isGameOver() {
        return waterCount <= 0 || foodCount <= 0;
    }

    /**
     * 沙暴日驻留在原地所消耗的资源
     */
    public static void StayForADayToConsume() {
        int deWater = weatherCost[day - 1][0];
        int deFood = weatherCost[day - 1][1];
        waterCount -= deWater;
        foodCount -= deFood;
        bagRemainCap -= deWater * WaterAndFood.WATER[0];
        bagRemainCap -= deFood * WaterAndFood.FOOD[0];
    }

    /**
     * 前往下一区域所消耗的资源
     */
    public static void costInOneStep() {
        int deWater = weatherCost[day - 1][0] * 2;
        int deFood = weatherCost[day - 1][1] * 2;
        waterCount -= deWater;
        foodCount -= deFood;
        bagRemainCap -= deWater * WaterAndFood.WATER[0];
        bagRemainCap -= deFood * WaterAndFood.FOOD[0];
    }

    /**
     * 显示信息
     */
    public static void showData() {
        System.out.println();
        System.out.println();
        System.out.println("第" + day + "天," + "当前位置为：" + temp.name + " 当前天气：" + getCurrentWeather());
        System.out.println("资金：" + wealth + "  背包容量：" + bagRemainCap
                + "  水剩余：" + waterCount + "  食物剩余：" + foodCount);
        System.out.println("当前可去的地方：");
        for (int j = 0; j < temp.next.length; j++) {
            System.out.print(temp.next[j].name + "  ");
        }
    }

    /**
     * 得到当前的天气信息
     *
     * @return 天气
     */
    public static String getCurrentWeather() {
        int t = weatherCost[day - 1][0];
        String weather = "";
        switch (t) {
            case 5:
                weather = "晴朗";
                break;
            case 8:
                weather = "高温";
                break;
            case 10:
                weather = "沙暴";
                break;
            default:
                break;
        }
        return weather;
    }

    /**
     * 初始化地图
     */
    public static Map[] initializeMap() {
        // 初始化区域
        Map[] maps = new Map[10];
        maps[0] = new Map(1);
        maps[1] = new Map(25);
        maps[2] = new Map(24);
        maps[3] = new Map(23);
        maps[4] = new Map(21);
        maps[5] = new Map(9);
        maps[6] = new Map(15);
        maps[7] = new Map(13);
        maps[8] = new Map(12);
        maps[9] = new Map(27);
        // 用链表连接各个区域
        maps[0].next = new Map[]{maps[1]};
        maps[1].next = new Map[]{maps[2]};
        maps[2].next = new Map[]{maps[3]};
        maps[3].next = new Map[]{maps[4]};
        maps[4].next = new Map[]{maps[5], maps[9]};
        maps[5].next = new Map[]{maps[6]};
        maps[6].next = new Map[]{maps[7]};
        maps[7].next = new Map[]{maps[8]};



        return maps;
    }

    /**
     * 初始化天气花费
     */
    public static int[][] initializeWeather() {
        int[][] weatherCost = new int[30][2];
        /*
            高温	高温	晴朗	沙暴	晴朗	高温	沙暴	晴朗	高温	高温
        */
        weatherCost[0] = Weather.HIGH_TEMPERATURE;
        weatherCost[1] = Weather.HIGH_TEMPERATURE;
        weatherCost[2] = Weather.SUNNY;
        weatherCost[3] = Weather.SANDSTORM;
        weatherCost[4] = Weather.SUNNY;
        weatherCost[5] = Weather.HIGH_TEMPERATURE;
        weatherCost[6] = Weather.SANDSTORM;
        weatherCost[7] = Weather.SUNNY;
        weatherCost[8] = Weather.HIGH_TEMPERATURE;
        weatherCost[9] = Weather.HIGH_TEMPERATURE;
        // 沙暴	 高温	晴朗	高温	高温	高温	沙暴	沙暴	高温	高温
        weatherCost[10] = Weather.SANDSTORM;
        weatherCost[11] = Weather.HIGH_TEMPERATURE;
        weatherCost[12] = Weather.SUNNY;
        weatherCost[13] = Weather.HIGH_TEMPERATURE;
        weatherCost[14] = Weather.HIGH_TEMPERATURE;
        weatherCost[15] = Weather.HIGH_TEMPERATURE;
        weatherCost[16] = Weather.SANDSTORM;
        weatherCost[17] = Weather.SANDSTORM;
        weatherCost[18] = Weather.HIGH_TEMPERATURE;
        weatherCost[19] = Weather.HIGH_TEMPERATURE;
        //  晴朗	晴朗	高温	晴朗	沙暴	高温	晴朗	晴朗	高温	高温
        weatherCost[20] = Weather.SUNNY;
        weatherCost[21] = Weather.SUNNY;
        weatherCost[22] = Weather.HIGH_TEMPERATURE;
        weatherCost[23] = Weather.SUNNY;
        weatherCost[24] = Weather.SANDSTORM;
        weatherCost[25] = Weather.HIGH_TEMPERATURE;
        weatherCost[26] = Weather.SUNNY;
        weatherCost[27] = Weather.SUNNY;
        weatherCost[28] = Weather.HIGH_TEMPERATURE;
        weatherCost[29] = Weather.HIGH_TEMPERATURE;

        return weatherCost;

    }
}
