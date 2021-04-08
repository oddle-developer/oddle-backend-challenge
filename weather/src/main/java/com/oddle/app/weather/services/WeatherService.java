package com.oddle.app.weather.services;

import com.oddle.app.weather.data.WeatherResponse;

public interface WeatherService {

    /**
     * Get the current Weather from A city
     * @param cityName The City Name
     * @return Weather Data
     */
    WeatherResponse getCurrentWeather(String cityName);
}
