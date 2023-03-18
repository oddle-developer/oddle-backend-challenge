package com.oddle.app.weather.controller;

import com.oddle.app.weather.dto.GetWeatherByParamsRequestDto;
import com.oddle.app.weather.dto.GetWeatherResponseDto;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public GetWeatherResponseDto getWeatherByParams(@RequestParam String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }
}