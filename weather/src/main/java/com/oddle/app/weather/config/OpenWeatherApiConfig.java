package com.oddle.app.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "open-weather.api")
@Component
@Data
public class OpenWeatherApiConfig {

    private String key;

    private String currentWeatherUrl;

}
