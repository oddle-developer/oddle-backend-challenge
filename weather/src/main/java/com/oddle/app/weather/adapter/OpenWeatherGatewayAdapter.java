package com.oddle.app.weather.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.oddle.app.weather.config.OpenWeatherConfig;
import com.oddle.app.weather.dto.response.openweather.OWCurrentWeatherResponse;

@Component
public class OpenWeatherGatewayAdapter {
	
	@Autowired
	private OpenWeatherConfig openWeatherConfig;

	public OWCurrentWeatherResponse getCurrentWeather(String city) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(openWeatherConfig.getCurrentWeatherUri())
		        .queryParam("q", city)
		        .queryParam("appid", openWeatherConfig.getKey());
		
		return restTemplate.getForEntity(builder.toUriString(), OWCurrentWeatherResponse.class).getBody();
	}
}
