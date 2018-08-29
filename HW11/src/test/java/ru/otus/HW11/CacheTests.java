package ru.otus.HW11;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.HW09.models.UserDataSet;

public class CacheTests {

    private DBServiceCachedImpl dbService;

    @Test
    public void eternalCache() {
        dbService = new DBServiceCachedImpl();

        UserDataSet user1 = new UserDataSet("Mike", 27);

        dbService.save(user1);

        Assert.assertEquals(dbService.getCacheSize(), 0);
        Assert.assertEquals(dbService.getCacheMissesCount(), 0);
        Assert.assertEquals(dbService.getCacheHitsCount(), 0);

        UserDataSet user2 = dbService.read(1);
        Assert.assertEquals(dbService.getCacheSize(), 1);
        Assert.assertEquals(dbService.getCacheMissesCount(), 1);
        Assert.assertEquals(dbService.getCacheHitsCount(), 0);

        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user1.getAge(), user2.getAge());

        dbService.read(1);
        Assert.assertEquals(dbService.getCacheMissesCount(), 1);
        Assert.assertEquals(dbService.getCacheHitsCount(), 1);

    }

    @Test
    public void eternalCacheWithMaxSize() {
        int cacheMaxSize = 5;

        dbService = new DBServiceCachedImpl(cacheMaxSize);

        dbService.save(new UserDataSet("Mike", 27));
        dbService.save(new UserDataSet("Maria", 23));
        dbService.save(new UserDataSet("Jane", 50));
        dbService.save(new UserDataSet("Jake", 72));
        dbService.save(new UserDataSet("Robert", 12));
        dbService.save(new UserDataSet("Steve", 15));
        dbService.save(new UserDataSet("Brad", 20));

        for (int i = 1; i <=7; i++) {
            dbService.read(i);
        }

        Assert.assertEquals(dbService.getCacheSize(), cacheMaxSize);
        Assert.assertEquals(dbService.getCacheMissesCount(), 7);
        Assert.assertEquals(dbService.getCacheHitsCount(), 0);

        for (int i = 7; i > 0; i--) {
            dbService.read(i);
        }

        Assert.assertEquals(dbService.getCacheMissesCount(), 9);
        Assert.assertEquals(dbService.getCacheHitsCount(), 5);
    }

}