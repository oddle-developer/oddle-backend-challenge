package com.oddle.app.weather.controller;

import com.oddle.app.weather.dto.GetWeatherByParamsRequestDto;
import com.oddle.app.weather.dto.GetWeatherResponseDto;
import com.oddle.app.weather.dto.SaveWeatherDataRequestDto;
import com.oddle.app.weather.dto.SaveWeatherDataResponseDto;
import com.oddle.app.weather.service.WeatherService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weathers")
    @ResponseBody
    public GetWeatherResponseDto getWeatherByName(@RequestParam String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }

    @PostMapping("/weathers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SaveWeatherDataResponseDto saveWeatherData(@RequestBody SaveWeatherDataRequestDto requestDto) throws NotFoundException {
        return weatherService.saveWeatherData(requestDto);
    }
}