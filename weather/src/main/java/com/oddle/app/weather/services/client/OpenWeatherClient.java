package com.oddle.app.weather.services.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.data.json.openweather.payload.CurrentPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@PropertySource("classpath:open-weather.properties")
public class OpenWeatherClient {

    private RestTemplate defaultTemplate;

    @Value("${open-weather.api.param}")
    private String apiParam;

    @Value("${open-weather.api.key}")
    private String apiKey;

    @Value("${open-weather.api.uri}")
    private String currentUri;

    @Value("${current.path}")
    private String currentPath;

    @Value("${current.param.city}")
    private String currentCityParam;

    @Autowired
    public OpenWeatherClient(RestTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public CurrentPayload fetchCurrentWeather(String city) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CurrentPayload currentPayload = null;
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(currentUri)
                .path("/" + currentPath)
                .queryParam(currentCityParam, city)
                .queryParam(apiParam, apiKey);
        ResponseEntity<String> response = defaultTemplate.getForEntity(
                builder.toUriString(),
                String.class
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            currentPayload = objectMapper.readValue(responseBody, CurrentPayload.class);
        }
        return currentPayload;
    }

}
