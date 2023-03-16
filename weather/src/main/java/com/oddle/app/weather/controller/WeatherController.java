package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.response.Response;
import com.oddle.app.weather.service.OpenWeatherService;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    OpenWeatherService openWeatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping("/current")
    public Response getCurrentWeather(@RequestParam String cityName) {
//        return Response.success(weatherService.getWeatherByCityName(cityName));
        Map<String, Object> params = new HashMap<>();
        params.put("lat", 44.34);
        params.put("lon", 10.99);
        return Response.success(openWeatherService.getCurrentWeather(params));
    }

}