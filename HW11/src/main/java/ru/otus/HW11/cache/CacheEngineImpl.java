package ru.otus.HW11.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private Map<K, CacheElement<K, V>> cache;

    private int maxSize;
    private long timeOfLife;
    private long timeOfIdle;
    private boolean isEternal;

    private int hitsCount = 0;
    private int missesCount = 0;

    private final Timer timer = new Timer();

    public long PERIOD = 30_000;

    public CacheEngineImpl() {
        isEternal = true;
        cache = new LinkedHashMap<>();
    }

    public CacheEngineImpl(long timeOfLife, long timeOfIdle) {
        isEternal = false;
        this.timeOfIdle = timeOfIdle;
        this.timeOfLife = timeOfLife;
        cache = new LinkedHashMap<>();

        if (timeOfLife != 0) {
            TimerTask lifeTimerTask = getTimerTask(
                lifeElement -> lifeElement.getCreationTime() + timeOfLife
            );
            timer.schedule(lifeTimerTask, 0, PERIOD);
        }
        if (timeOfIdle != 0) {
            TimerTask idleTimerTask = getTimerTask(
                idleElement -> idleElement.getLastAccessTime() + timeOfIdle
            );
            timer.schedule(idleTimerTask, 0, PERIOD);
        }
    }

    @Override
    public void put(K key, V value) {
        if(maxSize != 0 && cache.size() == maxSize) {
            cache.remove(
                cache.keySet().iterator().next()
            );
        }
        cache.put(key, new CacheElement<>(key, value));
    }

    @Override
    public V get(K key) {
        if (cache.containsKey(key) && cache.get(key).getValue() != null) {
            hitsCount++;
            cache.get(key).setLastAccessTime(System.currentTimeMillis());
            return cache.get(key).getValue();
        }
        missesCount++;
        return null;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setTimeOfLife(long timeMs) {
        this.timeOfLife = timeMs;
    }

    public void setTimeOfIdle(long timeMs) {
        this.timeOfLife = timeMs;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public int getMissesCount() {
        return missesCount;
    }

    public void dispose() {
        if(timer != null) {
            timer.cancel();
        }
    }

    private TimerTask getTimerTask(Function<CacheElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                cache.forEach((k, v) -> {
                    CacheElement<K, V> element = cache.get(k);
                    if (element == null || element.getValue() == null ||
                            isT1BeforeT2(
                                timeFunction.apply(element),
                                System.currentTimeMillis()
                            )
                        ) {
                        cache.remove(k);
                    }
                });
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

}
