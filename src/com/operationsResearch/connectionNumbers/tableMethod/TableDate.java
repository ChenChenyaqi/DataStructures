package com.operationsResearch.connectionNumbers.tableMethod;

// 表中每个空的元素类型，包含sigma检验数和原本花费和运输量
public class TableDate {
    // 检验数
    public double sigma;
    // 花费
    public double cost;
    // 某地到某地运输多少个
    public double count;
    // count的值是0 并非 空
    public boolean countZero;
    // 在矩阵中的坐标
    public int x;
    public int y;

    public TableDate(double cost, int x, int y) {
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String str = "(";
        if (sigma == 0) {
            str += "  ";
        } else {
            str += sigma;
        }
        str += ", " + cost + ", ";
        if (count == 0 && !countZero) {
            str += "  ";
        } else {
            str += count;
        }
        str += ") ";
        return str;
    }
}
