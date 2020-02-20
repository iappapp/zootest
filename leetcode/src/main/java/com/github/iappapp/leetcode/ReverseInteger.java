package com.github.iappapp.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseInteger {
    public static int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        System.out.println(n);
        return (int) n == n ? (int) n : 0;
    }


    public static String intToRoman(int num) {
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String reps[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String res = "";
        for (int i = 0; i < 13; i++) {
            while (num >= values[i]) {
                num -= values[i];
                res += reps[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(400));
        System.out.println(intToRoman(3999));
        System.out.println(intToRoman(401));
    }
}
