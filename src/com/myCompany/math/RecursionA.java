package com.myCompany.math;

import java.util.Hashtable;

/**
 * @author chenyaqi
 * @date 2021/6/8 - 18:37
 */
public class RecursionA {
    final static Hashtable<Pair, Boolean> mp = new Hashtable<>();
    // 将图中的四个特殊点分为四类 0：起点  1：村庄  2：矿山  3：终点
    final static int[] qua = {0, 1, 2, 3};
    // 四个特殊点之间的距离矩阵
    final static int[][] dist = {{0, 6, 8, 3},
            {6, 0, 2, 3},
            {8, 2, 0, 5},
            {3, 3, 5, 0}};
    // 四个特殊点的互相到达的决策情况
    final static int[][] f = {{0, 1, 1, 1},
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {0, 0, 0, 0}};
    // 天气情况，1,2,3分别代表晴朗，高温和沙暴
    final static int[] wea = {2, 2, 1, 3, 1,
            2, 3, 1, 2, 2,
            3, 2, 1, 2, 2,
            2, 3, 3, 2, 2,
            1, 1, 2, 1, 3,
            2, 1, 1, 2, 2};
    // mx和my分别是水和食物的重量
    final static int mx = 3, my = 2;
    // cx和cy分别是水和食物的基准价格
    final static int cx = 5, cy = 10;
    // sx中下标为1-3的元素分别指晴朗，高温，沙暴天气下水的基础消耗
    final static int[] sx = {0, 5, 8, 10};
    // sy中下标为1-3的元素分别指晴朗，高温，沙暴天气下食物的基础消耗
    final static int[] sy = {0, 7, 6, 10};
    // 共有四个特殊点
    final static int n = 4;
    // 背包容量
    final static int maxm = 1200;
    // 起始总资产
    final static int coins = 10000;
    // 挖矿每日收益
    final static int base = 1000;
    // 截止日期
    final static int date = 30;
    // 第d天从第i点走到第j点所消耗的水
    static int[][][] costx = new int[32][4][4];
    // 第d天从第i点走到第j点所消耗的食物
    static int[][][] costy = new int[32][4][4];
    // 第d天从第i点走到第j点所需要的的实际天数
    static int[][][] days = new int[32][4][4];
    static int ans = 0;
    static int[] rec = new int[32];
    // 每一天所到达的点的标记 -1 代表此时处于最短路径上的某个普通点或此时已经到达终点
    // 其余的数字 分别代表当天玩家位于对应的特殊点 对应情况如qua数组所示
    static int[] act = new int[32];
    // 每一天的特殊行动情况 2代表挖矿 1代表于矿山停止行动 0代表在村庄购买
    // ansx与ansact是最优解路劲和最优解路劲上的行为
    static int[] ansx = new int[32];
    static int[] ansact = new int[32];
    // ansg和ansh是最优解对应的初始水和食物资源量
    static int ansg, ansh;
    // 用于枚举的初始水和食物资源量
    static int g, h;

    public static void main(String[] args) {
        for (int d = 0; d <= date; d++) {
            rec[d] = -1;
            act[d] = -1;
        }
        for (int d = 0; d < date; d++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (f[qua[i]][qua[j]] == 1) {
                        int now = 0, count = 0, sumx = 0, sumy = 0;
                        while (count < dist[i][j]) {
                            if (wea[now + d] != 3) {
                                count++;
                                sumx += 2 * sx[wea[now + d]];
                                sumy += 2 * sy[wea[now + d]];
                            } else {
                                sumx += sx[wea[now + d]];
                                sumy += sy[wea[now + d]];
                            }
                            now++;
                            if (now + d >= date) {
                                break;
                            }
                        }
                        if (count < dist[i][j]) {
                            sumx = sumy = 20000;
                            now = 30;
                        }
                        costx[d][i][j] = sumx;
                        costy[d][i][j] = sumy;
                        days[d][i][j] = now;
                    }
                }
            }
        }
        for (int i = 0; i <= maxm; i++) {
            g = i / mx;
            h = (maxm - i) / my;
            Pair pair = new Pair(g, h);
            boolean isCon = false;
            boolean value = true;
            for (Pair temp : mp.keySet()) {
                if (temp.getFist() == pair.getFist() && temp.getSecond() == pair.getSecond()) {
                    isCon = true;
                    value = mp.get(temp);
                    break;
                }
            }
            if (!isCon) {
                mp.put(pair, false);
            }
            if (isCon) {
                if (!value) {
                    dfs(0, 0, 0, coins - g * cx - h * cy, g, h, -1);
                }
            } else{
                dfs(0, 0, 0, coins - g * cx - h * cy, g, h, -1);
            }
            mp.replace(pair, true);
        }
        for (int i = 0; i <= date; i++) {
            System.out.println(i + ":" + ansx[i] + ";" + ansact[i]);
        }
        System.out.println(ans + " " + ansg + " " + ansh);
    }

    public static void dfs(int day, int now, int nm, int c, int x, int y, int type) {
        act[day] = type;
        rec[day] = now;

        if (qua[now] == 3) {
            if (ans <= c + x * cx + y * cy) {
                ansg = g;
                ansh = h;
                ans = c + x * cx + y * cy;
                for (int i = 0; i <= date; i++) {
                    ansx[i] = rec[i];
                }
                for (int i = 0; i <= date; i++) {
                    ansact[i] = act[i];
                }
            }
            act[day] = -1;
            rec[day] = -1;
            return;
        }
        if (day >= date) {
            act[day] = -1;
            rec[day] = -1;
            return;
        }
        if (qua[now] == 1) {
            nm = maxm - mx * x - my * y;
        }
        for (int i = 0; i < n; i++) {
            if (f[qua[now]][qua[i]] == 1) {
                int tx = costx[day][now][i];
                int ty = costy[day][now][i];
                int ucost = c;
                int ux, uy;
                int um = nm;
                if (x >= tx) {
                    ux = x - tx;
                } else {
                    ux = 0;
                    ucost -= 2 * (tx - x) * cx;
                    um -= (tx - x) * mx;
                }
                if (y >= ty) {
                    uy = y - ty;
                } else {
                    uy = 0;
                    ucost -= 2 * (ty - y) * cy;
                    um -= (ty - y) * my;
                }
                if (ucost < 0 || um < 0) {
                    continue;
                }
                dfs(day + days[day][now][i], i, um, ucost, ux, uy, 0);
            }
            if (qua[now] == 2) {
                int attday = day;
                int tx = sx[wea[attday]];
                int ty = sy[wea[attday]];
                attday++;
                if (x >= tx) {
                    x -= tx;
                    tx = 0;
                } else {
                    tx -= x;
                    x = 0;
                }
                if (y >= ty) {
                    y -= ty;
                    ty = 0;
                } else {
                    ty -= y;
                    y = 0;
                }
                nm -= tx * mx + ty * my;
                c -= 2 * tx * cx + 2 * ty * cy;
                if (nm >= 0 && c >= 0) {
                    dfs(attday, now, nm, c, x, y, 1);
                }
                attday = day;
                tx = sx[wea[attday]] * 2;
                ty = sy[wea[attday]] * 2;
                attday++;
                if (x >= tx) {
                    x -= tx;
                    tx = 0;
                } else {
                    tx -= x;
                    x = 0;
                }
                if (y >= ty) {
                    y -= ty;
                    ty = 0;
                } else {
                    ty -= y;
                    y = 0;
                }
                nm -= tx * mx + ty * my;
                c -= 2 * tx * cx + 2 * ty * cy;
                c += base;
                if (nm >= 0 && c >= 0) {
                    dfs(attday, now, nm, c, x, y, 2);
                }
            }
            rec[day] = -1;
            act[day] = -1;
        }
    }
}
