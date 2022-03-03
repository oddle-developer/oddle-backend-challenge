package com.oddle.app.weather.service;

import com.oddle.app.weather.pojo.dto.WeatherDTO;

import java.util.List;

public interface WeatherService {
    List<WeatherDTO> getAllWeather();

    WeatherDTO getWeatherById(int weatherId);

    WeatherDTO saveWeather(WeatherDTO weatherDto);

    WeatherDTO updateWeather(int weatherId, WeatherDTO weatherDto);

    void deleteWeather(int weatherId);
}
