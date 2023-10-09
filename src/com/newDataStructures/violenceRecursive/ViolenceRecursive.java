package com.newDataStructures.violenceRecursive;

/**
 * 暴力递归（回溯） 就是 尝试
 * <p>
 * 1.把问题转化为规模缩小了的同类问题的子问题
 * 2.有明确的不需要继续进行递归的条件（base case）
 * 3.有当得到了子问题的结果之后的决策过程
 * 4.不记录每一个子问题的解
 * <p>
 * 一定要学会怎么去尝试，因为这是动态规划的基础
 * </p>
 */
public class ViolenceRecursive {

    // 经典汉诺塔问题
    public static void hanoi(int n){
        if(n > 0) {
            func(n, "左", "中", "右");
        }
    }

    public static void func(int i, String start, String end, String other){
        if(i == 1){
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            func(i - 1, start, other, end);
            System.out.println("Move " + i  + " from " + start + " to " + end);
            func(i - 1, other, end, start);
        }
    }

    public static void main(String[] args) {
        int n = 9;
        hanoi(n);
    }
}
