package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.WeatherLogDto;

public interface WeatherService {
	WeatherLogDto getWeatherByCityName(String city);

	WeatherLogDto save(WeatherLogDto dto);

	WeatherLogDto getPastPeriod(String city, String date);

	void deleteById(Long id);

	void updateWeather(WeatherLogDto dto);
}
