package com.oddle.app.weather.data.mapper;

public interface JsonMapper<T> {
    T mapJsonToPojo(String body);

    String mapPojoToJson(T object);
}
