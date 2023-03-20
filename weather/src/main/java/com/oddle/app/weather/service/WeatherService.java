package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.WeatherLogDto;
import java.util.Date;
import java.util.List;

public interface WeatherService {

	/**
	 * Get weather by city name
	 * @param city String - city name
	 * @return WeatherLogDto
	 */
	WeatherLogDto getWeatherByCityName(String city);

	/**
	 * Save weather to database
	 * @param dto weatherLogDto
	 * @return WeatherLogDto
	 */
	WeatherLogDto save(WeatherLogDto dto);

	/**
	 * Get past period by city, start date, end date
	 * @param city
	 * @param startDate
	 * @param endDate
	 * @return List of weatherLogDto
	 */
	List<WeatherLogDto> getPastPeriod(String city, Date startDate, Date endDate);

	/**
	 * Delete weatherLog by id
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * Update weatherLog record
	 * @param dto
	 */
	void updateWeather(WeatherLogDto dto);
}
