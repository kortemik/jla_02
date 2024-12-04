package com.teragrep.jla_02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicLong messages = new AtomicLong();
        AtomicBoolean run = new AtomicBoolean(true);

        ForkJoinPool.commonPool().submit(new Logback(run, countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(run, countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(run, countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(run, countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(run, countDownLatch, messages));

        try {
            Thread.sleep(15_000);
            System.out.println("sleep complete");
            run.set(false);
            System.out.println("set run false");
            countDownLatch.await();
            System.out.println("shutdown complete");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("messages sent " + messages.get());
    }
}
