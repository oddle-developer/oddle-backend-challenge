package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.model.dto.WeatherDTO;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public List<WeatherDTO> getWeatherByCityName(String cityName) {
        return null;
    }
}
