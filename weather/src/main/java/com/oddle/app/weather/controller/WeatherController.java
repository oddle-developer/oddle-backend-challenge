package com.oddle.app.weather.controller;

import com.oddle.app.weather.dto.WeatherLogDto;
import com.oddle.app.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("")
    public Map<String, Object> getWeather(@RequestParam("city") String cityName) {
        return Collections.singletonMap("data", weatherService.getWeatherByCityName(cityName));
    }

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody WeatherLogDto weather){
        log.info("save - ", weather);
        weatherService.save(weather);
        return Collections.singletonMap("Message", "success");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id){
        log.info("delete - ", id);
        weatherService.deleteById(id);
        return Collections.singletonMap("Message", "success");
    }

    @GetMapping("/past")
    public Map<String, Object> getPastPeriod(@RequestParam("city") String cityName, @RequestParam("date") String date){
        log.info("getPastPeriod");
        return Collections.singletonMap("data", weatherService.getPastPeriod(cityName, date));
    }

    @PutMapping("")
    public Map<String, Object> update(@RequestBody WeatherLogDto dto){
        log.info("update - ", dto);
        weatherService.updateWeather(dto);
        return Collections.singletonMap("message", "success");
    }
}