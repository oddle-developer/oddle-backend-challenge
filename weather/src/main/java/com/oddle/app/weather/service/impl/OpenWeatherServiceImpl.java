package com.oddle.app.weather.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oddle.app.weather.config.OpenWeatherApiConfig;
import com.oddle.app.weather.helper.RestClientSupport;
import com.oddle.app.weather.model.dto.WeatherDTO;
import com.oddle.app.weather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherServiceImpl extends RestClientSupport implements OpenWeatherService {

    private static final String APP_KEY = "appid";

    private static final String CITY_KEY = "q";

    @Autowired
    OpenWeatherApiConfig config;

    @Override
    public WeatherDTO getCurrentWeather(String cityName) {
        Map<String, Object> params = new HashMap<>();
        params.put(CITY_KEY, cityName);
        params.put(APP_KEY, config.getKey());
        return get(config.getCurrentWeatherUrl(), null, new TypeReference<WeatherDTO>() {}, params);
    }
}
