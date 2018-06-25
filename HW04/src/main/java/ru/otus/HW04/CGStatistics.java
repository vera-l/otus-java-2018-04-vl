package ru.otus.HW04;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

class GCStatistics {

    private List<GCInfoItem> items;

    GCStatistics() {
        items = new ArrayList<GCInfoItem>();
    }

    public void add(GCInfoItem item) {
        items.add(item);
    }

    public void add(String action, String name, String cause, Long duration) {
        items.add(
            new GCInfoItem(action, name, cause, duration)
        );
    }

    public void nullify() {
        items = new ArrayList<GCInfoItem>();
    }

    public void printHeader() {
        System.out.println("------------------------------------------------" +
            "-------------------------------");
        System.out.println(
            String.format(
                "%-10s %-25s %-10s %-15s",
                "minute",
                "action",
                "count",
                "duration, ms"
            )
        );
        System.out.println("------------------------------------------------" +
            "-------------------------------");
    }

    public void print(int stats_time_iteration) {
        Map<String, LongSummaryStatistics> stat = items.stream()
            .collect(
                Collectors.groupingBy(
                    GCInfoItem::getAction,
                    Collectors.summarizingLong(
                        GCInfoItem::getDuration
                    )
                )
            );

        stat.forEach(
            (key, value) -> System.out.println(
                String.format(
                    "%-10s %-25s %-10s %-15s",
                    stats_time_iteration,
                    key,
                    value.getCount(),
                    value.getSum()
                )
            )
        );
        System.out.println("------------------------------------------------" +
            "-------------------------------");

    }
}