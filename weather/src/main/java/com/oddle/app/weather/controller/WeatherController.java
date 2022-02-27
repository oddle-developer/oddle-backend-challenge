package com.oddle.app.weather.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @DeleteMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode deleteWeather(@RequestParam("date") String date, @PathVariable String city) {
        return weatherService.delete(city, date);
    }

    @PutMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode updateWeather(@PathVariable String city, @RequestParam(value = "date", required = false) String date, @RequestBody(required = false) String body) {
        return weatherService.update(city, date, body);
    }

    @GetMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode getWeather(@PathVariable String city, @RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to, @RequestParam(value = "date", required = false) String date) {
        return weatherService.getWeather(city, from, to, date);
    }

    @PostMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode storeWeather(@PathVariable String city, @RequestParam(value = "date", required = false) String date, @RequestBody String body) {
        return weatherService.storeWeather(city, date, body);
    }
}