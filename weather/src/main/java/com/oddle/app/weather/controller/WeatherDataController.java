package com.oddle.app.weather.controller;

import com.oddle.app.weather.pojo.dto.WeatherDataDTO;
import com.oddle.app.weather.service.impl.WeatherDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherDataController {
    @Autowired
    private WeatherDataServiceImpl weatherDataService;


}
