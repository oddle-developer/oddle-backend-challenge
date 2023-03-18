package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.filter.HistoryWeatherFilter;
import com.oddle.app.weather.model.response.Response;
import com.oddle.app.weather.model.update.WeatherUpdate;
import com.oddle.app.weather.service.OpenWeatherService;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
        return Response.success(openWeatherService.getCurrentWeather(cityName));
    }

    @GetMapping("/history")
    public Response getHistoryWeather(HistoryWeatherFilter filter) {
        return Response.success(weatherService.getHistoryWeather(filter));
    }

    @PostMapping("/save")
    public Response saveWeather(@RequestBody WeatherUpdate update) {
        return weatherService.saveWeather(update);
    }

    @PutMapping("/edit")
    public Response editWeather(@RequestBody WeatherUpdate update) {
        return weatherService.editWeather(update);
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteWeatherHistory(@PathVariable Long id){
        return weatherService.deleteWeatherHistory(id);
    }

}