package com.github.iappapp.leetcode;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {

            int a = nums[i];
            result[0] = i;
            int b = target - a;

            for (int j = i + 1; j < nums.length && j > i; j++) {
                if (nums[j] == b) {
                    result[1] = j;
                    return result;
                }
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {
        for (int i : twoSum(new int[]{-1, -2, -3, -4, -5}, -8)) {
            System.out.println(i);
        }
    }
}
