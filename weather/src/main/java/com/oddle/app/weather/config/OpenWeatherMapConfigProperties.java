package com.oddle.app.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("openweathermap")
@Getter
@Setter
public class OpenWeatherMapConfigProperties {

	private String apiKey;

	private String url;
}