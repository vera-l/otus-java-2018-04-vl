package ru.otus.HW12;

import ru.otus.HW09.DBService;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW11.DBServiceCachedImpl;
import ru.otus.HW11.cache.CacheEngine;
import ru.otus.HW11.cache.CacheEngineImpl;

class Main {

    private static DBService dbService;
    private static final int SLEEP_MILLIS = 500;

    public static void main(String... args) throws Exception, InterruptedException {
        CacheEngine<Integer, UserDataSet> cacheEngine = new CacheEngineImpl<>(1000, 500);
        cacheEngine.setMaxSize(100);

        dbService = new DBServiceCachedImpl(cacheEngine);

        WebServer server = new WebServer(cacheEngine, dbService, 8080);
        server.start();
    }

}