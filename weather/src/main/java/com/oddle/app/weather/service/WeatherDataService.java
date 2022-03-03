package com.oddle.app.weather.service;

import com.oddle.app.weather.pojo.dto.WeatherDataDTO;

import java.util.List;

public interface WeatherDataService {
    List<WeatherDataDTO> getWeatherFromPastDate(String fromDate, String toDate);
    WeatherDataDTO getCityWeatherById(int weatherDataId);
    WeatherDataDTO saveWeatherData(WeatherDataDTO weatherDataDTO);
    WeatherDataDTO updateWeatherData(int weatherDataId, WeatherDataDTO weatherDataDTO);
    void deleteWeatherData(int weatherDataId);
}
