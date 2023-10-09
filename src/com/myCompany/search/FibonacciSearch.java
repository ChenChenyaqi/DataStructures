package com.myCompany.search;

import java.util.Arrays;

/**
 * @author chenyaqi
 * @date 2021/6/12 - 10:12
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 12);
        System.out.println("i = " + i);
    }

    // 编写斐波那契查找算法
    public static int fibSearch(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        // 存放mid值
        int mid = 0;
        // 获取斐波那契数列
        int[] f = fib();
        // 获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        // 不足的部分会用0填充
        int[] temp = Arrays.copyOf(nums, f[k]);
        // 实际上需要使用nums数组最后的元素填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = nums[high];
        }
        // 使用while来循环处理，找到我们的数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                // f[k] = f[k-1] + f[k-2];
                // 所以前面的f[k-1] = f[k-2] + f[k-3];
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                // f[k] = f[k-1] + f[k-2];
                // 所以后面的f[k-2] = f[k-3] + f[k-4];
                k -= 2;
            } else {
                // 确定返回哪个坐标
                if (mid <= high){
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    // 得到一个斐波那契数列
    // 因为后面我们mid = low + F(k-1)-1,需要使用到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

}
