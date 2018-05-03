package ru.otus.HW0201;

import java.lang.instrument.Instrumentation;

public class AgentMemoryCounter {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        AgentMemoryCounter.instrumentation = instrumentation;
    }

    public static long getSize(Object obj) {
        if (instrumentation == null) {
            throw new IllegalStateException("Agent is not initialized");
        }
        return instrumentation.getObjectSize(obj);
    }
}