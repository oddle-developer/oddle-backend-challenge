package com.oddle.app.weather.controller;

import com.oddle.app.weather.models.rest.Response;
import com.oddle.app.weather.models.rest.WeatherData;
import com.oddle.app.weather.service.WeatherService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController()
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;


    @GetMapping("/")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping(value = "/city/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getWeatherByCity(@NotNull @PathVariable("city_name") String city){

        return ResponseEntity.ok().body(weatherService.getWeatherByCity(city));
    }

    @PostMapping(value = "/data/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> saveWeatherData(@NotNull @RequestBody WeatherData weatherData){
        if(weatherService.saveWeatherData(weatherData))
            return ResponseEntity.ok().body(Response.builder().code("100")
              .message("Data successfully saved.").build());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder().code("500")
              .message("Error occurred while processing.").build());
    }

    @GetMapping(value = "history/city/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getCityWeatherHistory(@NotNull @PathVariable("city_name") String city){
        return ResponseEntity.ok().body(Response.builder().code("100")
          .message("Successfully retrieved.")
          .data(weatherService.getWeatherDatasByCity(city))
          .build());
    }

    @DeleteMapping(value = "record/{weather_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> removeWeatherRecord(@NotNull @PathVariable("weather_id") String weatherId){
        if(weatherService.removeWeatherData(weatherId))
            return ResponseEntity.ok().body(Response.builder().code("100")
                .message("Data successfully removed.").build());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder().code("404")
          .message("Weather ID not exists.").build());

    }

    @PutMapping(value = "/record/{weather_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateWeatherData(@NotNull @PathVariable("weather_id") String weatherId, @NotNull @RequestBody WeatherData weatherData){
        if(!weatherService.checkWeatherIdExist(weatherId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder().code("404")
              .message("Weather ID not exists.").build());

        return ResponseEntity.ok().body(Response.builder().code("100")
          .message("Data successfully updated.").data(weatherService.updateWeatherData(weatherId,weatherData)).build());
    }
}