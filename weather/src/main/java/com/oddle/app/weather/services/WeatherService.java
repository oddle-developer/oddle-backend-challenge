package com.oddle.app.weather.services;

import com.oddle.app.weather.data.AddRequest;
import com.oddle.app.weather.data.WeatherResponse;
import com.oddle.app.weather.exception.SaveOperationException;

import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

public interface WeatherService {

    /**
     * Get the current Weather from A city
     * @param cityName The City Name
     * @param timeZone The TimeZone
     * @return Weather Data
     */
    WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone);

    List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone);

    List<WeatherResponse> getWeatherInRange(String cityName, LocalDate fromDate, LocalDate toDate, int page, TimeZone timeZone);

    void addNewWeatherData(AddRequest addRequest) throws SaveOperationException;
}
