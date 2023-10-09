package com.myCompany.recursion;

/**
 * 给一个数组，A、B俩玩家只能从最左和最右拿走一个数字
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class BeforeAfterHand {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 100, 300, 1, 15, 260, 1561};
        int res = win1(arr);
        System.out.println(res);

        System.out.println(win2(arr));
    }

    private static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // A作为先手，B作为后手
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // A作为后手，B作为先手
        return Math.max(s(arr, 0, arr.length - 1), f(arr, 0, arr.length - 1));
    }

    // 后手
    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            // 作为后手，得不到数字
            return 0;
        }
        // 作为后手，拿不到i或者j，只能在下次作为先手，且拿到的是最小的，因为是后手
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    // 先手, 返回作为先手，在i，j范围上获得的最大数字
    private static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        // 拿i后，加上作为后手得到的数字 和 拿j后，作为后手得到的数字
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

}
