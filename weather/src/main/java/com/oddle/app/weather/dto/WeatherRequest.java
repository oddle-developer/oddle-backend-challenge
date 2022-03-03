package com.oddle.app.weather.dto;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherRequest {
    @NonNull
    private String city;
    @NonNull
    private JsonNode jsonData;
}
