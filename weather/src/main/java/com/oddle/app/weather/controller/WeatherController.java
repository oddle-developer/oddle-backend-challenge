package com.oddle.app.weather.controller;

import com.oddle.app.weather.dto.*;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.service.WeatherService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    //This endpoint would search the weather using open weather API by the city name
    @GetMapping("/weathers")
    @ResponseBody
    public GetWeatherByCityNameResponseDto getWeatherByName(@RequestParam String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }

    //This endpoint would save the weather for each city name in the request
    @PostMapping("/weathers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SaveWeatherDataResponseDto saveWeatherData(@RequestBody SaveWeatherDataRequestDto requestDto) throws NotFoundException {
        return weatherService.saveWeatherData(requestDto);
    }

    //This endpoint would get the saved weather using created_at date range or find all the data
    @GetMapping("/weathers/histories")
    public List<Weather> getWeatherByDateRange(@RequestBody(required = false) GetWeatherDataRequestDto requestDto) {
        return weatherService.getWeatherData(requestDto);
    }

    //This endpoint would delete the saved weather using the data's id
    @DeleteMapping("/weathers/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteWeatherById(@PathVariable("id") long id) throws NotFoundException {
        weatherService.deleteWeatherById(id);
    }

    //This endpoint would update the saved weather using the data's id
    @PatchMapping("/weathers/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Weather updateWeatherById(@PathVariable("id") long id, @RequestBody UpdateWeatherRequestDto requestDto) throws NotFoundException {
        return weatherService.updateWeatherById(id,requestDto);
    }

}