package ru.otus.HW11;

import ru.otus.HW09.DBServiceImpl;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW11.cache.CacheEngine;
import java.util.List;

public class DBServiceCachedImpl extends DBServiceImpl {

    private CacheEngine<Long, UserDataSet> cache;

    public DBServiceCachedImpl(CacheEngine cache) {
        super();
        this.cache = cache;
    }

    public DBServiceCachedImpl(CacheEngine cache, String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        this.cache = cache;
    }

    public DBServiceCachedImpl(CacheEngine cache, Integer cacheMaxSize) {
        this(cache);
        cache.setMaxSize(cacheMaxSize);
    }

    public DBServiceCachedImpl(CacheEngine cache, Integer cacheMaxSize,
                               String dbUrl, String dbUser, String dbPassword) {
        this(cache, dbUrl, dbUser, dbPassword);
        cache.setMaxSize(cacheMaxSize);
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

    @Override
    public List<UserDataSet> readAll() {
        List<UserDataSet> users = super.readAll();
        users.forEach(
            user -> cache.put(user.getId(), user)
        );

        return users;
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
