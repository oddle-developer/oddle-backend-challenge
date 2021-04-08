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

import java.time.*;
import java.util.*;

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
    public WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone) {
        ZonedDateTime todayAtZone = ZonedDateTime.of(LocalDateTime.now(), timeZone.toZoneId());
        LocalDate todayAtUTC = todayAtZone.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        Date startOfDay = Date.from(todayAtUTC.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(todayAtUTC.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
        Pageable firstResult = PageRequest.of(0, 1);
        List<Weather> resultFromDB = weatherRepository.findByCityInRangeDesc(
                cityName,
                startOfDay,
                endOfDay,
                firstResult
        );
        if (resultFromDB.size() == 0) {
            return null;
        }
        return Optional
                .ofNullable(resultFromDB.get(0))
                .map(weatherMapper::mapEntityToResponse)
                .orElse(null);
    }
}
