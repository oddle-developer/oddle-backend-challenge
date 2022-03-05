package com.oddle.app.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.dto.HistoricalDto;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping("/api/v1/weather")
    public WeatherResponse getCurrentWeather(@RequestParam("city") String city) throws IOException {
        return weatherService.getCurrent(city);
    }

    @GetMapping("/api/v1/historical-weather")
    public HistoricalDto getHistoricalWeather(@RequestParam("city") String city,
                                              @RequestParam("date") String date) throws JsonProcessingException {
        return weatherService.getHistorical(city, date);
    }

    @PostMapping("/api/v1/historical-weather")
    public HistoricalDto saveHistorical(@RequestParam("city") String city,
                                        @RequestParam("date") String date) throws JsonProcessingException {
        return weatherService.saveHistorical(city, date);
    }

    @DeleteMapping("/api/v1/historical-weather")
    public void deleteWeather(@RequestParam("city") String city) {
        weatherService.deleteWeather(city);
    }

    @PatchMapping("/api/v1/historical-weather")
    public Weather updateWeather(@RequestBody HistoricalDto requestBody) {
        return weatherService.updateWeather(requestBody);
    }

}