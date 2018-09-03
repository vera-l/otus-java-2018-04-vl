package ru.otus.HW11.cache;

import java.util.HashMap;

public interface CacheEngine<K, V> {

    void put(K key, V value);

    V get(K key);

    int size();

    void setMaxSize(int maxSize);

    void setTimeOfLife(long timeMs);

    void setTimeOfIdle(long timeMs);

    int getHitsCount();

    int getMissesCount();

    void dispose();

    HashMap<String, Object> getStatistics();

}