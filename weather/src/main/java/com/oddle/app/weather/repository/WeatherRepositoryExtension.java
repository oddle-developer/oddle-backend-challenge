package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;

public interface WeatherRepositoryExtension {
    public IWeatherSummary getWeatherByCityAndDate(String city, String date);
}
