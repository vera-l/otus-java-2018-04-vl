package ru.otus.HW04;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;
import java.lang.management.ManagementFactory;

public class Main {

    private static final int DEFAULT_DURATION_MS = 120_000;

    public static void main(String... args) throws InterruptedException {
        System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName());

        if (args.length == 0) {
            new Test().run();
        } else {
            new Test().run(DEFAULT_DURATION_MS);
        }
    }

}
