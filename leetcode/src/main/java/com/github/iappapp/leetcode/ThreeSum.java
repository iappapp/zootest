package com.github.iappapp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * （1）首先对数组进行排序（从小到大）
 * （2）依次取出第 i 个数（i从0开始），并且不重复的选取（跳过重复的数）
 * （3）这样问题就转换为 2 个数求和的问题（可以用双指针解决方法）2 数求和问题
 * （4）定义两个指针：左指针（left） 和 右指针（right）
 * （5）找出固定 left， 此时left所指的位置为数组中最小数，再找到两个数和 不大于 target 的最大 right 的位置
 * （6）调整 left 的位置（后移），求解和是否为 target O(n)
 * （7）时间复杂度：O(nlogn) + O(n)
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeSumList = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int first;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            if ((first = nums[i]) > 0) {
                break;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (first + nums[left] + nums[right] < 0) ++left;
                if (first + nums[left] + nums[right] > 0) --right;
                if (first + nums[left] + nums[right] == 0) {
                    if (left == right) {
                        break;
                    }
                    List<Integer> threeSum = new ArrayList<>(3);
                    threeSum.add(first);
                    threeSum.add(nums[left]);
                    threeSum.add(nums[right]);
                    threeSumList.add(threeSum);

                    ++left;
                    --right;
                    System.out.println("left= " + left + " right= " + right + " i= " + i);

                    while (left < right && nums[left] == nums[left - 1]) {
                        ++left;
                    }

                    while (left < right && nums[right] == nums[right + 1]) {
                        --right;
                    }
                }
            }
        }

        return threeSumList;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
