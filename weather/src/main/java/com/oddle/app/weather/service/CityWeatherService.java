package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.exception.CurrentCityWeatherException;

public interface CityWeatherService {
	public WeatherResponse getCurrentWeather(String city) throws CurrentCityWeatherException;
}
