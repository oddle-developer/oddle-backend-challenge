package com.oddle.app.weather.services.impl;

import com.oddle.app.weather.data.WeatherResponse;
import com.oddle.app.weather.data.mapper.WeatherMapper;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.repositories.WeatherRepository;
import com.oddle.app.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    private final WeatherMapper weatherMapper;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public WeatherResponse getCurrentWeather(String cityName) {
        LocalDate today = LocalDate.now();
        Pageable firstResult = PageRequest.of(0, 1);
        List<Weather> resultFromDB = weatherRepository.findByCityInRangeDesc(
                cityName,
                today.atStartOfDay(),
                today.atTime(LocalTime.MAX),
                firstResult
        );
        return Optional
                .ofNullable(resultFromDB.get(0))
                .map(weatherMapper::mapEntityToResponse)
                .orElse(null);
    }
}
