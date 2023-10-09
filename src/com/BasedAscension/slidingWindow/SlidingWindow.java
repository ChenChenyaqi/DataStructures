package com.BasedAscension.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口
 * <p>
 * 快速得到此时窗口内的最大值
 * <p>
 * 双端队列
 */
public class SlidingWindow {

    public static int[] maxNumInArr(int[] nums, int w) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - w + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
            if(!deque.isEmpty() && deque.peekFirst() == i - w){
                deque.pollFirst();
            }
            if( i >= w - 1 && !deque.isEmpty()){
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
