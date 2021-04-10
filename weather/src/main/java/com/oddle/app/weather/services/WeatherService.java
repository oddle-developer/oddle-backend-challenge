package com.oddle.app.weather.services;

import com.oddle.app.weather.data.transfer.AddRequest;
import com.oddle.app.weather.data.transfer.WeatherResponse;
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

    /**
     * Get the history weather data
     * @param cityName The city name
     * @param timeZone The Time Zone
     * @return Weather Data
     */
    List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone);

    /**
     * Get the weather data from a city within a range
     *
     * @param cityName The city name
     * @param fromDate The Start Date
     * @param toDate The End Date
     * @param page The Page Request to limit the data
     * @param timeZone The Time Zone
     * @return The Weather Data
     */
    List<WeatherResponse> getWeatherInRange(String cityName, LocalDate fromDate, LocalDate toDate, int page, TimeZone timeZone);

    /**
     * Add a new weather data to the database
     *
     * @param addRequest The Add Request
     * @return The Weather Added id
     * @throws SaveOperationException When Save with duplication
     */
    String addNewWeatherData(AddRequest addRequest) throws SaveOperationException;
}
