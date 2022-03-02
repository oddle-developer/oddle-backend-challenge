package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.openweather.ResponseOpenWeatherDTO;

public interface OpenWeatherService {
    ResponseOpenWeatherDTO getCurrentWeather(double lat, double lon);
    ResponseOpenWeatherDTO getCurrentWeatherByCityName(String cityName);
}
