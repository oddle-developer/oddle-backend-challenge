package com.oddle.app.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.dto.HistoricalDto;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.entity.Weather;

public interface WeatherService {
    WeatherResponse getCurrent(String city);
    HistoricalDto getHistorical(String city, String date) throws JsonProcessingException;
    HistoricalDto saveHistorical(String city, String date) throws JsonProcessingException;
    void deleteWeather(String city);
    Weather updateWeather(HistoricalDto historicalDto);
}
