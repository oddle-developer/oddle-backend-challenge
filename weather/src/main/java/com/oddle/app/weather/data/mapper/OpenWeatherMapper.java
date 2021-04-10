package com.oddle.app.weather.data.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.data.json.openweather.payload.CurrentPayload;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class OpenWeatherMapper {

    public CurrentPayload mapCurrentWeatherData(String payload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(payload, CurrentPayload.class);
    }
}
