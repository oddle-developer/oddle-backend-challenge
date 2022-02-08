package com.oddle.app.weather.controller;

import com.oddle.app.weather.request.WeatherRequest;
import com.oddle.app.weather.service.WeatherService;
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

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping(path = "/weather")
    public ResponseEntity<Object> getWeatherByCityName(@RequestParam(name = "city") String city) {
        Map<String, Object> response = weatherService.getWeatherByCityName(city);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/weather")
    public ResponseEntity<Object> createNewWeather(@RequestBody WeatherRequest request) {
        Map<String, Object> response = weatherService.createNewWeather(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/weathers")
    public ResponseEntity<Object> getAllWeathers(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        Map<String, Object> response = weatherService.getAllWeathers(page, limit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/weather/{id}")
    public ResponseEntity<Object> updateWeather(@PathVariable(name = "id")String id, @RequestBody WeatherRequest request) {
        Map<String, Object> response = weatherService.updateWeather(Long.parseLong(id), request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/weather/{id}")
    public ResponseEntity<Object> removeWeather(@PathVariable(name = "id")String id){
        Map<String, Object> response = weatherService.removeWeather(Long.parseLong(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}