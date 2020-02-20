package com.github.iappapp.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMedianSortedArray {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> integers = new ArrayList<>(nums1.length + nums2.length);

        for (int i : nums1) {
            integers.add(i);
        }

        for (int i : nums2) {
            integers.add(i);
        }

        if (integers.size() == 0) {
            return 0.0;
        }

        Collections.sort(integers);

        double median = 0.0D;
        if (integers.size() % 2 == 0) {
            int left = integers.get(integers.size() / 2 - 1);
            int right = integers.get(integers.size() / 2);
            median = (left + right) / 2.0D;
            System.out.println(left + " " + right);
        } else {
            median = integers.get((0 + integers.size()) / 2);
            System.out.println(median);
        }

        return median;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
    }
}
