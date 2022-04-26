package com.oddle.app.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "open-weather.api")
@Component
@Data
public class OpenWeatherConfig {
	private String key;
	private String currentWeatherUri;
}
