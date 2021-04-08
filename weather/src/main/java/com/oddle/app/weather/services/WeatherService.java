package com.oddle.app.weather.services;

import com.oddle.app.weather.data.WeatherResponse;

import java.util.TimeZone;

public interface WeatherService {

    /**
     * Get the current Weather from A city
     * @param cityName The City Name
     * @param timeZone The TimeZone
     * @return Weather Data
     */
    WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone);
}
