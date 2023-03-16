package com.oddle.app.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "open-weather.api")
@Component
public class OpenWeatherApiConfig {

    private String key;

    private String currentWeatherUrl;

}
