package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.WeatherDataEntity;
import com.oddle.app.weather.pojo.response.WeatherResponse;
import com.oddle.app.weather.repository.WeatherDataRepository;
import com.oddle.app.weather.response.Status;
import com.oddle.app.weather.response.StatusBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {
    @Autowired
    private Environment env;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

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

//            map pojo object to entity
            ModelMapper modelMapper = new ModelMapper();
            WeatherDataEntity weatherDataEntity = modelMapper.map(weatherData, WeatherDataEntity.class);
            System.out.println("aaa");
            weatherDataRepository.save(weatherDataEntity);
        } catch (Exception e) {
            response.put("status", StatusBuilder.getStatus(Status.CITY_NOT_FOUND.name()));
        }
        return response;
    }

}
