package com.myCompany.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenyaqi
 * @date 2021/8/5 - 10:30
 */
public class SumOfThreeNumber {
    //    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
//    使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组
//    注意：答案中不可以包含重复的三元组。
//    输入：nums = [-1,0,1,2,-1,-4]
//    输出：[[-1,-1,2],[-1,0,1]]
    public static void main(String[] args) {
        int[] nums = new int[]{-4,2,2};
        List<List<Integer>> res = threeSum(nums);
        System.out.println("res = " + res);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null){
            return res;
        }
        int len = nums.length;
        if (len < 3){
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int firstNum = nums[i];
            int targetSum = -firstNum;
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                if (nums[left] + nums[right] < targetSum){
                    left++;
                } else if(nums[left] + nums[right] > targetSum){
                    right--;
                } else {
                    res.add(Arrays.asList(firstNum,nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
