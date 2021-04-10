package com.oddle.app.weather.services;

import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.exception.FetchException;

import java.util.List;
import java.util.TimeZone;

public interface WeatherFetchService {

    /**
     * Get the current Weather from A city
     * @param cityName The City Name
     * @param timeZone The TimeZone
     * @return Weather Data
     */
    WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone) throws FetchException;

    /**
     * Get the history weather data
     * @param cityName The city name
     * @param timeZone The Time Zone
     * @return Weather Data
     */
    List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone) throws FetchException;

}
