package com.oddle.app.weather.controller;

import com.oddle.app.weather.pojo.dto.WeatherDTO;
import com.oddle.app.weather.pojo.response.WeatherResponse;
import com.oddle.app.weather.response.Status;
import com.oddle.app.weather.response.StatusBuilder;
import com.oddle.app.weather.service.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {
    @Autowired
    private Environment env;

    @Autowired
    private WeatherServiceImpl weatherService;

    private RestTemplate restTemplate;
    private String apiKey;

    @PostConstruct
    public void initialize() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder.build();
        this.apiKey = env.getProperty("API_KEY");
    }

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping("/weather")
    public HashMap<String, Object> getPropertyValue(@RequestParam(value = "city", defaultValue = "London") String cityName) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", cityName, apiKey);
        HashMap<String, Object> response = new HashMap<>();
        try {
            WeatherResponse weatherData = restTemplate.getForObject(url, WeatherResponse.class);
            response.put("status", StatusBuilder.getStatus(Status.SUCCESS.name()));
            response.put("response", weatherData);
        } catch (Exception e) {
            response.put("status", StatusBuilder.getStatus(Status.CITY_NOT_FOUND.name()));
        }
        return response;
    }

    @GetMapping("/weathers")
    public List<WeatherDTO> getAllWeather() {
        return weatherService.getAllWeather();
    }

    @GetMapping("/weathers/{weatherId}")
    public WeatherDTO getWeatherById(@PathVariable("weatherId") int weatherId) {
        return weatherService.getWeatherById(weatherId);
    }

    @PostMapping("/weathers")
    public WeatherDTO saveWeather(@RequestBody WeatherDTO weatherDto) {
        return weatherService.saveWeather(weatherDto);
    }

    @PutMapping("/weathers/{weatherId}")
    public WeatherDTO updateWeather(@PathVariable("weatherId") int weatherId, @RequestBody WeatherDTO weatherDto) {
        return weatherService.updateWeather(weatherId, weatherDto);
    }

    @DeleteMapping("/weathers/{weatherId}")
    public String deleteWeather(@PathVariable("weatherId") int weatherId) {
        weatherService.deleteWeather(weatherId);
        return "Deleted Successfully";
    }

}
