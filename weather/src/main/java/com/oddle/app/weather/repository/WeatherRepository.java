package com.oddle.app.weather.repository;

import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.Weather;

import java.util.List;

public interface WeatherRepository {

    List<Weather> getWeather(String city, String from, String to) throws WeatherException;

    Weather getWeather(String city, String date) throws WeatherException;

    void store(Weather weather) throws WeatherException;

    void update(Weather weather) throws WeatherException;

    void delete(Weather weather) throws WeatherException;
}
