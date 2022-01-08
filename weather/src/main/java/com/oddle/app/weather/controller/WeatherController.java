package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullFields;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import com.oddle.app.weather.dto.BaseResponse;
import com.oddle.app.weather.dto.WeatherRequest;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.service.WeatherService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class WeatherController {
    @Autowired
    WeatherService service;

    @Value("${threshold.max-days}")
    private Integer daysThreshold;
    

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping(value="/weather")
    public ResponseEntity<WeatherResponse> getWeatherByCity(@RequestParam("city") String city) {
        
        WeatherResponse res = service.getWeatherResponse(city);

        return ResponseEntity.ok(res);
    }
    
    @PostMapping(value = "/weather")
    public ResponseEntity<WeatherResponse> saveWeather(@RequestBody WeatherRequest request) {
        WeatherResponse res = service.saveWeather(request);

        return ResponseEntity.ok(res);
    }

    @GetMapping(value = "/weathers")
    public ResponseEntity<List<WeatherResponse>> getWeatherHistory(@RequestParam(value = "fromDt", required = false) String fromDt, @RequestParam(value = "toDt", required = false) String toDt) {
        LocalDateTime fromDateTime = LocalDateTime.now().minusDays(daysThreshold);
        LocalDateTime toDateTime = LocalDateTime.now();
        String statusMsg = service.isRequestValid(fromDt, toDt, fromDateTime, toDateTime); 
        if(statusMsg.equalsIgnoreCase("OK")){
            List<WeatherResponse> list = service.getWeathersHistory(fromDateTime, toDateTime);
            if(list.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(WeatherResponse.builder().message("No history between dates.").build()));
            } else {
                return ResponseEntity.ok(list);
            }
        }else{
            return ResponseEntity.badRequest().body(List.of(WeatherResponse.builder().message(statusMsg).build()));
        }
    }

    @DeleteMapping(value = "/weather/{id}")
    public ResponseEntity<BaseResponse> removeWeather(@PathVariable(value = "id", required = true) Long id) {
        if(id == null) {
            return ResponseEntity.badRequest().body(BaseResponse.builder().message("id cannot be empty").build());
        }
        service.deleteWeather(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/weather/{id}")
    public ResponseEntity<BaseResponse> editWeather(@PathVariable(value = "id", required = true) Long id, @RequestBody WeatherRequest request) {
        service.updateWeather(id, request);
        return ResponseEntity.noContent().build();
    }
}