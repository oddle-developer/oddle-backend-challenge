package com.oddle.app.weather.service;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import com.oddle.app.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public IWeatherSummary getWeatherByCityAndDate(String city, String date) {
        return weatherRepository.getWeatherByCityAndDate(city, date);
    }

    public IWeatherSummary save(IWeatherSummary ws) {
        return weatherRepository.save(ws);
    }

    public Optional<IWeatherSummary> getWeatherById(Long id) {
        return weatherRepository.findById(id);
    }

    public void delete(Long id) {
        weatherRepository.deleteById(id);
    }

    public List<IWeatherSummary> getWeathers(String city, Date startDate, Date endDate, int offset, int limit) {
        return weatherRepository.getWeathers(city, startDate, endDate, offset, limit);
    }
}
