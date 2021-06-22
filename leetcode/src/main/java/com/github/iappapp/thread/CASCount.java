package com.github.iappapp.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CASCount {


    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> threadList = new ArrayList<>(600);
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            threadList.add(thread);
        }
        int i = 0;
        for (Thread t : threadList) {
            t.setName("thread_" + i);
            i++;
            t.start();
        }

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
    }

    static class Counter {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        private int i = 0;

        private void count() {
            i++;
        }

        private void safeCount() {
            for (;;) {
                int i = atomicInteger.get();
                boolean success = atomicInteger.compareAndSet(i, ++i);
                if (success) {
                    System.out.println(String.format("safe count i=%s", i));
                    break;
                } else {
                    System.out.println(String.format("race count i=%s", i));
                }
            }
        }
    }
}
