package com.oddle.app.weather.cache;

import lombok.Data;

@Data
public class CacheModel<T> {

    private T data;

    private Long expiredTime;
}
