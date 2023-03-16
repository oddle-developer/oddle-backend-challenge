package com.oddle.app.weather.service;

import com.oddle.app.weather.model.dto.open.weather.OpenWeatherDTO;

import java.util.Map;

public interface OpenWeatherService {

    OpenWeatherDTO getCurrentWeather(Map<String, Object> params);
}
