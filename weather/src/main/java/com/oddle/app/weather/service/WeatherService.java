package com.oddle.app.weather.service;


import com.oddle.app.weather.models.repositories.WeatherRecord;
import com.oddle.app.weather.models.rest.Response;
import com.oddle.app.weather.models.rest.WeatherData;

import java.util.List;

public interface WeatherService {

  public Response getWeatherByCity(String city);
  public boolean saveWeatherData(WeatherData weatherData);
  public List<WeatherData> getWeatherDatasByCity(String city);
  public boolean removeWeatherData(String weatherId);
  public WeatherRecord updateWeatherData(String weatherId, WeatherData weatherData);

  public boolean checkWeatherIdExist(String weatherId);
}
