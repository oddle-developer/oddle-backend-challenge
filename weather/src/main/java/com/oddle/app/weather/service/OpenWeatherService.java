package com.oddle.app.weather.service;

import com.oddle.app.weather.model.dto.WeatherDTO;

public interface OpenWeatherService {

    WeatherDTO getCurrentWeather(String cityName);
}
