package com.oddle.app.weather.services;

import com.oddle.app.weather.data.json.oddle.payload.AddRequest;
import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.exception.oddle.SaveOperationOddleFetchException;

import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

public interface WeatherService extends WeatherFetchService {

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
     * @throws SaveOperationOddleFetchException When Save with duplication
     */
    String addNewWeatherData(AddRequest addRequest) throws SaveOperationOddleFetchException;
}
