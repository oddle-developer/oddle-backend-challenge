package com.oddle.app.weather.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oddle.app.weather.config.OpenWeatherApiConfig;
import com.oddle.app.weather.helper.RestClientSupport;
import com.oddle.app.weather.model.dto.open.weather.OpenWeatherDTO;
import com.oddle.app.weather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenWeatherServiceImpl extends RestClientSupport implements OpenWeatherService {

    private static final String APP_KEY = "appid";

    @Autowired
    OpenWeatherApiConfig config;

    @Override
    public OpenWeatherDTO getCurrentWeather(Map<String, Object> params) {
        params.put(APP_KEY, config.getKey());
        return get(config.getCurrentWeatherUrl(), null, new TypeReference<OpenWeatherDTO>() {}, params);
    }
}
