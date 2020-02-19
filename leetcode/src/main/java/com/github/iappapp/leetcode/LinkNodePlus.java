package com.github.iappapp.leetcode;


import com.github.iappapp.modal.Node;

import java.util.Stack;

public class LinkNodePlus {

    public static Node plus(Node a, Node b) {
        return plus2Node(a, b, 0);
    }

    public static Node plus2Node(Node a, Node b, int i) {
        //i为进位数
        if (a == null && b == null && i < 1)
            return null;
        int value = i;
        if (a != null)
            value += a.data;
        if (b != null)
            value += b.data;
        Node result = new Node(value % 10);
        result.next = plus2Node(a == null ? null : a.next, b == null ? null : b.next, value >= 10 ? 1 : 0);
        return result;
    }

    // 打印方法
    public static void print(Node head) {
        if (head == null)
            return;
        print(head.next);
        System.out.print(head.data);
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        a.next = new Node(2);
        a.next.next = new Node(3);
        a.next.next.next = new Node(4);

        Node b = new Node(4);
        b.next = new Node(3);
        b.next.next = new Node(2);
        b.next.next.next = new Node(1);

        Node c = addTwoNumbers(a, b);
        print(c);
    }

    public static Node addTwoNumbers(Node l1, Node l2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node index1 = l1, index2 = l2;
        int length1 = 0, length2 = 0;
        // 进位符
        int carry = 0;
        while (index1 != null || index2 != null) {
            if (index1 != null) {
                stack1.push(index1);
                index1 = index1.next;
                length1++;
            }
            if (index2 != null) {
                stack2.push(index2);
                index2 = index2.next;
                length2++;
            }
        }
        if (length1 > length2) {
            while (!stack1.isEmpty()) {
                Node temp1 = stack1.pop();
                int val2 = stack2.isEmpty() ? 0 : stack2.pop().data;
                int result = temp1.data + val2 + carry;
                temp1.data = result % 10;
                carry = result / 10;
            }
            if (carry == 1) {
                Node newNode = new Node(1);
                newNode.next = l1;
                return newNode;
            } else {
                return l1;
            }
        } else {
            while (!stack2.isEmpty()) {
                int val1 = stack1.isEmpty() ? 0 : stack1.pop().data;
                Node temp2 = stack2.pop();
                int result = val1 + temp2.data + carry;
                temp2.data = result % 10;
                carry = result / 10;
            }
            if (carry == 1) {
                Node newNode = new Node(1);
                newNode.next = l2;
                return newNode;
            } else {
                return l2;
            }
        }
    }
}
