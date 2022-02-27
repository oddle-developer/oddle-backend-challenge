package com.oddle.app.weather.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface WeatherService {

    public JsonNode getWeather(String city, String from, String to, String date);

    public JsonNode update(String city, String date, String info);

    public JsonNode delete(String city, String date);

    public JsonNode storeWeather(String city, String date, String body);
}
