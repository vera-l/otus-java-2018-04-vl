package ru.otus.HW04;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    private final int DEFAULT_ITER_SIZE = 120;
    private final int SLEEP_MILLIS = 10;
    private ArrayList<Long> data;
    private Long currentItemValue;
    private int iterSize;
    private long startTime;

    public Test() {
        this.iterSize = DEFAULT_ITER_SIZE;
    }

    public void run() throws InterruptedException {
        run(null);
    }

    public void run(Integer durationMs) throws InterruptedException {
        data = new ArrayList<>();
        currentItemValue = 0L;
        startTime = System.currentTimeMillis();

        if (durationMs != null) {
            Timer timer = new Timer();
            TimerTask exitApp = new TimerTask() {
                public void run() {
                    printGCstatistics(ManagementFactory.getGarbageCollectorMXBeans(), durationMs);
                    System.exit(0);
                }
            };
            timer.schedule(exitApp, new Date(
                System.currentTimeMillis() + durationMs)
            );
        }

        while(true) {
            data.addAll(addItems());
            Thread.sleep(SLEEP_MILLIS);
            data.remove(data.size() - 1);
            if(durationMs == null) {
                printIterInfo();
            }
        }
    }

    private ArrayList<Long> addItems() {
        ArrayList<Long> tempArray = new ArrayList<>(iterSize);
        for (int i = 0; i < iterSize; i++) {
            tempArray.add(currentItemValue++);
        }
        return tempArray;
    }

    private void printIterInfo() {
        System.out.printf(
            "%s minutes: %s array items\n",
            (System.currentTimeMillis() - startTime) / 1000 / 60,
            data.size()
        );
    }

    private void printGCstatistics(List<GarbageCollectorMXBean> gcBeans, int durationMs) {
        System.out.println("------------------------------------------------" +
            "-------------------------------");
        System.out.println(
            String.format(
                "%-25s %-10s %-12s %-10s %-12s",
                "name",
                "count",
                "count/min",
                "time, ms",
                "time/min, ms"
            )
        );
        System.out.println("------------------------------------------------" +
            "-------------------------------");
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println(
                String.format(
                    "%-25s %-10s %-12.2f %-10s %-12.2f",
                    gcBean.getName(),
                    gcBean.getCollectionCount(),
                    (double) gcBean.getCollectionCount() * 1000 / durationMs,
                    gcBean.getCollectionTime(),
                    (double) gcBean.getCollectionTime() * 1000 / durationMs
                )
            );
        }
        System.out.println("------------------------------------------------" +
            "-------------------------------");
    }
}
