package com.oddle.app.weather.service;

import com.oddle.app.weather.model.filter.HistoryWeatherFilter;
import com.oddle.app.weather.model.response.Response;
import com.oddle.app.weather.model.update.WeatherUpdate;

import java.util.Map;

public interface WeatherService {

    Response saveWeather(WeatherUpdate update);

    Response editWeather(WeatherUpdate update);

    Map<String, Object> getHistoryWeather(HistoryWeatherFilter filter);

    Response deleteWeatherHistory(Long weatherId);
}
