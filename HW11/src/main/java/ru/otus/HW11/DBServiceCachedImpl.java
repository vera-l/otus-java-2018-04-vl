package ru.otus.HW11;

import ru.otus.HW09.DBServiceImpl;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW11.cache.CacheEngine;
import ru.otus.HW11.cache.CacheEngineImpl;

public class DBServiceCachedImpl extends DBServiceImpl {

    private CacheEngine<Long, UserDataSet> cache;

    public DBServiceCachedImpl() {
        super();
        cache = new CacheEngineImpl();
    }

    public DBServiceCachedImpl(Integer cacheMaxSize) {
        super();
        cache = new CacheEngineImpl();
        cache.setMaxSize(cacheMaxSize);
    }

    public DBServiceCachedImpl(Long cacheTimeOfLife, Long cacheTimeOfIdle) {
        super();
        cache = new CacheEngineImpl(cacheTimeOfLife, cacheTimeOfIdle);
    }

    @Override
    public UserDataSet read(long userId) {
        UserDataSet result = cache.get(userId);
        if(result == null) {
            result = super.read(userId);
            cache.put(userId, result);
        }
        return result;
    }

    public int getCacheSize() {
        return cache.size();
    }

    public int getCacheHitsCount() {
        return cache.getHitsCount();
    }

    public int getCacheMissesCount() {
        return cache.getMissesCount();
    }

    public void shutdown() {
        cache.dispose();
    }

}
