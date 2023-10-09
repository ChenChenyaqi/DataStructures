package com.intermediateClass.lesson1;

/**
 * 滑动窗口: 在一个有序数组，每个数字代表数轴上的一个点，求长度为len的绳子，最大能覆盖多少个点？
 *          left依次指向数组上的点，right往下走，如果走到不能再走，记录当前覆盖的点，left往下走
 *
 * 打表法（输入：n, 输出: n.  用基础代码自己实现输出结果，然后查看输出的规律）
 *
 * 先手和后手，吃 n 份草
 * 一次只能吃 4 的 i 次方 份，如 1， 4， 16， 64
 * 谁吃完了谁赢
 */
public class EatGrass {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + winner2(i));
        }
    }

    public static String winner1(int n){
        // 0  1  2  3  4
        // 后 先 后 先 先
        if(n < 5){
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        // n > 5
        int base = 1; // 先手决定吃的草
        while(base <= n){
            // n - base 是留给后手吃的，母过程是先手，子过程就是后手
            if(winner1(n - base).equals("后手")){
                return "先手";
            }
            if(base > n / 4){ // 防止 base * 4 之后溢出
                break;
            }
            // 依次试着吃 1  4  16  64
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n){
        int t = n % 5;
        String win1 = "先手";
        String win2 = "后手";
        String win = "";
        switch (t){
            case 0:
            case 2:
                win = win2;
                break;
            case 1:
            case 3:
            case 4:
                win = win1;
                break;
            default:
                break;
        }
        return win;
    }
}
