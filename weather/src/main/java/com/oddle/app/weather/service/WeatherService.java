package com.oddle.app.weather.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.oddle.app.weather.dto.request.weather.save.EditWeatherDataRequest;
import com.oddle.app.weather.dto.request.weather.save.SaveWeatherDataRequest;
import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.exception.DateException;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.weatherdata.Weather;

public interface WeatherService {
	public Weather save(SaveWeatherDataRequest saveWeatherDataRequest) throws DateException;
	public Weather edit(EditWeatherDataRequest editWeatherDataRequest) throws DateException,WeatherException;
	public Weather delete(Long id) throws WeatherException;
	public Page<WeatherResponse> history(String cityName,LocalDate from, LocalDate to,int page, int size);
}
