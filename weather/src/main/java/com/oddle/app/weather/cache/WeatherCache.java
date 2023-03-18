package com.oddle.app.weather.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class WeatherCache {

    private static final Map<String, CacheModel> cacheMap = new HashMap<>();

    /**
     * 1000L*60*60*24*30 equals to 1 month which has 30 days
     * To convert to timestamp just multiply it with 100
     * DEFAULT EXPIRED TIME will be 10minutes
     */
    private static final long DEFAULT_EXPIRED_TIME = 600000L;

    /*
      Since this class is used for cache, so prevent from creating instance
     */
    private WeatherCache() {

    }

    public static <T> void putData(String key, T data, Long expiredTime) {
        Long timeExpired = Optional.ofNullable(expiredTime).orElse(DEFAULT_EXPIRED_TIME);
        CacheModel<T> cacheData = new CacheModel<>();
        cacheData.setData(data);
        cacheData.setExpiredTime(System.currentTimeMillis() + timeExpired);
        cacheMap.put(key, cacheData);
    }

    public static CacheModel getData(String key) {
        CacheModel data = cacheMap.get(key);
        if (Objects.isNull(data)) {
            return null;
        }
        if (System.currentTimeMillis() >= data.getExpiredTime()) {
            cacheMap.remove(key);
            return null;
        } else {
            return data;
        }
    }

    public static <T> void updateData(String key, T data) {
        CacheModel<T> cachesData = new CacheModel<>();
        cachesData.setData(data);
        cachesData.setExpiredTime(System.currentTimeMillis() + DEFAULT_EXPIRED_TIME);
        cacheMap.put(key, cachesData);
    }

    public static <T> void removeData(String key) {
        cacheMap.remove(key);
    }
}
