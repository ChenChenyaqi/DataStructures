package com.intermediateClass.lesson2;

/**
 * 给一个非负整数，代表二叉树节点个数。返回能形成多少种不同的二叉树结构
 */
public class BinaryTreeStructures {

    // 暴力递归
    public static int process(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        if(n == 2){
            return 2;
        }

        int res = 0;
        for (int leftNum = 0; leftNum <= n - 1; leftNum++) {
            int leftWays = process(leftNum);
            int rightWays = process(n - 1 - leftNum);
            res += leftWays * rightWays;
        }
        return res;
    }

    // dp
    public static int dpProcess(int n){
        if(n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) { // 节点个数为 i 的时候
            for (int j = 0; j <= i - 1; j++) { // 左侧节点个数为 j - 1, 右侧节点个数为 i - j
                dp[i] += dp[j] * dp[i - j - 1];
            }

        }
        return dp[n];
    }
}
