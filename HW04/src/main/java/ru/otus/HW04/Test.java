package ru.otus.HW04;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
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
    private final int STATS_TIME_ITERATION_MS = 60_000;
    private final int TIME_RESERVE = 2_000;
    private int stats_time_iteration;
    private ArrayList<Long> data;
    private Long currentItemValue;
    private int iterSize;
    private long startTime;
    GCStatistics stats;

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
        stats_time_iteration = 1;
        stats = new GCStatistics();

        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from(
                        (CompositeData) notification.getUserData()
                    );

                    stats.add(
                        info.getGcAction().replace("end of ", ""),
                        info.getGcName(),
                        info.getGcCause(),
                        info.getGcInfo().getDuration()
                    );

                }
            };

            emitter.addNotificationListener(listener, null, null);

        }


        if (durationMs != null) {
            Timer timer = new Timer();
            TimerTask exitApp = new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            };
            timer.schedule(exitApp, new Date(
                System.currentTimeMillis() + durationMs + TIME_RESERVE)
            );
        }

        runStatsCalc(true);

        while(true) {
            data.addAll(addItems());
            Thread.sleep(SLEEP_MILLIS);
            data.remove(data.size() - 1);
            if(durationMs == null) {
                printIterInfo();
            }
        }
    }

    private void runStatsCalc(boolean isFirstRun) {
        if (isFirstRun) {
            stats.printHeader();
        }

        Timer timer = new Timer();
        TimerTask printStatForIteration = new TimerTask() {
            public void run() {
                stats.print(stats_time_iteration);
                stats.nullify();
                stats_time_iteration++;
                runStatsCalc(false);
            }
        };
        timer.schedule(
            printStatForIteration,
            new Date(
                System.currentTimeMillis() + STATS_TIME_ITERATION_MS
            )
        );
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

}
