package com.operationsResearch.connectionNumbers.tableMethod;

// 位势，在行/列 中的 行/列号，位势值
// rowOrCol = 0  行
// rowOrCol = 1  列
public class Potential {
    // 是行位势还是列位势，0 行位势，1 列位势
    public int rowOrCol;
    // 所处的行号/列号
    public int index;
    // 位势值
    public double potential;
    // 是否计算过该位势
    public boolean isComputed;

    public Potential(int index, int rowOrCol) {
        this.index = index;
        this.rowOrCol = rowOrCol;
    }

    @Override
    public String toString() {
        return potential + "";
    }
}
