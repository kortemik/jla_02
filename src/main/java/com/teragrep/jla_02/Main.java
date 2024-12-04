package com.teragrep.jla_02;

import java.text.SimpleDateFormat;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        final int sleep_duration = ((System.getenv("SLEEP_DURATION") == null) ? 5 : Integer.parseInt(System.getenv("SLEEP_DURATION")))*1000;

        Timer timer = new Timer();

        System.out.println("Sending events every " + sleep_duration + " milliseconds");


        Thread Logback = new Thread(() -> {
            System.out.println("Starting Logback");
            timer.scheduleAtFixedRate(new Logback(), 0, sleep_duration);
        });
        Logback.start();


        try {
            Thread.currentThread().join();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
