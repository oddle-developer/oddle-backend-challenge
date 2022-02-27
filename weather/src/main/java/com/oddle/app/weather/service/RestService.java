package com.oddle.app.weather.service;

import com.oddle.app.weather.exception.WeatherException;

import java.util.Map;

public interface RestService {

    public String sendMessage(String destination, Map<String, String> queryParam) throws WeatherException;
}
