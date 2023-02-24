package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;

import java.util.Date;
import java.util.List;

public interface WeatherRepositoryExtension {
    public IWeatherSummary getWeatherByCityAndDate(String city, String date);
    public List<IWeatherSummary> getWeathers(String city, Date startDate, Date endDate, int offset, int limit);
}
