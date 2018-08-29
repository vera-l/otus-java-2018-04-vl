package ru.otus.HW11.cache;

import java.lang.ref.SoftReference;

class CacheElement<K, V> {

    private final K key;
    private final SoftReference<V> valueReference;
    private final long creationTime;
    private long lastAccessTime;

    CacheElement(K key, V value) {
        this.key = key;
        this.valueReference = new SoftReference<>(value);
        this.creationTime = System.currentTimeMillis();
    }

    public K getKey() {
        setLastAccessTime(System.currentTimeMillis());
        return key;
    }

    public V getValue() {
        return valueReference.get();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long timeMs) {
        this.lastAccessTime = timeMs;
    }

}


