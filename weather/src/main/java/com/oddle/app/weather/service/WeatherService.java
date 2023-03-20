package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.WeatherLogDto;
import java.util.Date;
import java.util.List;

public interface WeatherService {
	WeatherLogDto getWeatherByCityName(String city);

	WeatherLogDto save(WeatherLogDto dto);

	List<WeatherLogDto> getPastPeriod(String city, Date startDate, Date endDate);

	void deleteById(Long id);

	void updateWeather(WeatherLogDto dto);
}
