package com.github.iappapp;

public class Solution {

    public static int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;
        int cur = nums[0];  //当前跳到的最远距离
        int next = nums[0]; //下一步能跳到的最远距离
        int step = 1;
        for (int i = 1; i < n; i++) {
            System.out.println("cur=" + cur + " i=" + i + " next=" + next);
            if (cur < i) {
                cur = next;
                step++;
            }
            System.out.println(" i=" + i + " nums[ " + i + "]=" + nums[i]);
            if (i + nums[i] > next) {
                next = i + nums[i];
            }
            if (cur >= n - 1)
                return step;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] list = new int[]{2, 3, 1, 1, 4, 2, 5};
        System.out.println(jump(list));
    }
}
