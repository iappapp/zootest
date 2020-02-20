package com.github.iappapp.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class MaxQueue {
    public Queue<Integer> queue;
    public Deque<Integer> maxQueue;

    public MaxQueue() {
        queue = new ArrayDeque<>(32);
        maxQueue = new ArrayDeque<>(32);
    }

    public int max_value() {
        if (!maxQueue.isEmpty()) {
            return maxQueue.peek();
        }

        return -1;
    }

    public void push_back(int value) {
        queue.add(value);

        while (!maxQueue.isEmpty() && value > maxQueue.getLast()) {
            maxQueue.pollLast();
        }

        maxQueue.add(value);
    }

    public int pop_front() {
        if (!queue.isEmpty()) {
            int answer = queue.poll();

            if (answer == maxQueue.peek()) {
                maxQueue.poll();
            }
            return answer;
        }

        return -1;
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(323);
        maxQueue.push_back(24);
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.queue);
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.queue);
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.queue);
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.queue);
    }
}
