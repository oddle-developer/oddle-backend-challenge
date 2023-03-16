package com.oddle.app.weather.service;

import com.oddle.app.weather.model.dto.open.weather.OpenWeatherDTO;

public interface OpenWeatherService {

    OpenWeatherDTO getCurrentWeather();
}
