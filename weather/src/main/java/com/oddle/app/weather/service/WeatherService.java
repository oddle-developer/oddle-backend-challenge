package com.oddle.app.weather.service;

import com.oddle.app.weather.model.dto.WeatherDTO;

import java.util.List;

public interface WeatherService {

    List<WeatherDTO> getWeatherByCityName(String cityName);
}
