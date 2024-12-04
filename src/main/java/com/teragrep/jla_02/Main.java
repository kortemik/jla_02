package com.teragrep.jla_02;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(99999);
        AtomicLong messages = new AtomicLong();

        ForkJoinPool.commonPool().submit(new Logback(countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(countDownLatch, messages));

        ForkJoinPool.commonPool().submit(new Logback(countDownLatch, messages));

        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("should have 99999*3 messages sent " + messages.get());
    }
}
