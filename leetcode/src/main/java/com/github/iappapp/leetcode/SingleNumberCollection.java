package com.github.iappapp.leetcode;

import java.util.List;
import java.util.Stack;

public class SingleNumberCollection {

    public static int[] singleNumbers(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        for (int number : nums) {
            if (stack.isEmpty() || !stack.contains(number)) {
                stack.push(number);
            } else {
                ((List)stack) .remove((Integer) number);
            }
        }
        int[] result = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = singleNumbers(new int[]{1, 2, 2, 3, 4, 4, 4, 4, 5, 4 ,5, 6});

        for (int i : result) {
            System.out.println(i);
        }
    }
}
