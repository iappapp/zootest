package com.github.iappapp.leetcode;

import java.util.*;

public class LeetCode {

    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> integerMap = new HashMap<>(32);
        for (int num : nums) {
            if (integerMap.containsKey(num)) {
                integerMap.put(num, integerMap.get(num) + 1);
            } else {
                integerMap.put(num, 1);
            }
        }

        for (Map.Entry entry : integerMap.entrySet()) {
            if ((Integer) entry.getValue() == 1) {
                return (Integer) entry.getKey();
            }
        }

        return 0;
    }

    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     *
     * Example 1:
     *
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     *
     * Example 2:
     *
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();

        Stack<CharIndex> characterStack = new Stack<>();

        List<Integer> indexList = new ArrayList<>(chars.length);

        for (int i = 0; i < s.length(); i++) {
            indexList.add(i, 0);
        }

        int next = 0;
        for (char c : chars) {
            int previous = next;
            next++;

            if (c == '(') {
                characterStack.push(new CharIndex(c, previous));
                indexList.set(previous, 1);
            }

            if (c == ')') {
                if (!characterStack.isEmpty()) {
                    CharIndex top = characterStack.peek();
                    if (top.getaChar() == '(') {
                        characterStack.pop();
                        indexList.set(top.getIndex(), 0);
                    } else {
                        characterStack.push(new CharIndex(c, previous));
                        indexList.set(previous, 1);
                    }
                } else {
                    indexList.set(previous, 1);
                }
            }
        }
        System.out.println(indexList);
        int max = 0;
        int count = 0;
        for (int i= 0 ; i < indexList.size();  i++) {
            if (indexList.get(i) == 0) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
            }
        }
        return max;
    }

    public static class CharIndex {
        private char aChar;
        private int index;

        public char getaChar() {
            return aChar;
        }

        public void setaChar(char aChar) {
            this.aChar = aChar;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public CharIndex() {
        }

        public CharIndex(char aChar, int index) {
            this.aChar = aChar;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CharIndex charIndex = (CharIndex) o;
            return getaChar() == charIndex.getaChar() &&
                    getIndex() == charIndex.getIndex();
        }

        @Override
        public int hashCode() {

            return Objects.hash(getaChar(), getIndex());
        }
    }

    public static char firstUniqeChar(String s) {
        char[] charArray = s.toCharArray();

        Map<Character, Integer> charIntegerMap = new HashMap<>(32);
        List<Character> characterList = new ArrayList<>(100);
        for (char c : charArray) {
            if (charIntegerMap.containsKey(c)) {
                charIntegerMap.put(c, charIntegerMap.get(c) + 1);
            } else {
                charIntegerMap.put(c, 1);
                characterList.add(c);
            }
        }

        for (Character c : characterList) {
            if (charIntegerMap.getOrDefault(c, 0) == 1) {
                return c;
            }
        }

        return ' ';
    }

    /**
     * 翻转单词顺序 LCOF
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String[] strings = s.split(" ");

        StringBuilder sb = new StringBuilder(s.length());
        for (int i = strings.length - 1; i >= 0; i-- ) {
            if (strings[i].equals("") || strings[i] == " ") {
                continue;
            }
            sb.append(new String(strings[i].trim()));
            System.out.println(strings[i].trim());
            sb.append(" ");
        }
        int length = sb.toString().length();
        if (length == 0) {
            return "";
        }
        return sb.toString().substring(0, length - 1).trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(""));
    }
}
