package com.myCompany.tenAlgorithm;

/**
 * @author chenyaqi
 * @date 2021/5/29 - 10:01
 */
public class DivideAndConquerAlgorithm {
    // 以汉诺塔为例
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    private static void hanoiTower(int num, char start, char other, char end) {
        // 如果只有一个盘 A -> C
        if (num == 1){
            // 第一个盘直接从start到end
            System.out.println("第1个盘从 " + start + "->" + end);
        } else {
            // 以 n>=2 为例，我们总是把上面的整体看作一个盘与最底层的盘共同组成两个盘
            // 首先上面的盘 A -> B , 中间会用到C
            // 先移动上面num - 1个盘，从start 到 other
            hanoiTower(num -1 , start, end, other);
            // 再把最底层 num盘 从 start 到 end
            System.out.println("第" + num + "个盘从 " + start + "->" + end);
            // 把上面的盘 B -> C , 中间会用到A
            // num - 1 盘从 other 到 end
            hanoiTower(num-1, other, start, end);
        }

    }
}
