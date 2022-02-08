package com.oddle.app.weather.controller;

import com.oddle.app.weather.request.WeatherRequest;
import com.oddle.app.weather.response.CommonResponse;
import com.oddle.app.weather.response.WeatherResponse;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/weather")
    public ResponseEntity<CommonResponse> getWeatherByCityName(@RequestParam(name = "city") String city) {
        CommonResponse<WeatherResponse> response = new CommonResponse<>();
        WeatherResponse resObj = weatherService.getWeatherByCityName(city);
        if(resObj == null){
            response.failedResponse("City not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.successResponse("OK", resObj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/weather")
    public ResponseEntity<CommonResponse> createNewWeather(@RequestBody WeatherRequest request) {
        CommonResponse<WeatherResponse> response = new CommonResponse();
        WeatherResponse resObj = weatherService.createNewWeather(request);
        response.successResponse("Data successfully created!", resObj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/weathers")
    public ResponseEntity<CommonResponse> getAllWeathers(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        CommonResponse<List<WeatherResponse>> response = new CommonResponse();
        List<WeatherResponse> resObjList = weatherService.getAllWeathers(page, limit);
        response.successResponse("OK", resObjList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/weather/{id}")
    public ResponseEntity<CommonResponse> updateWeather(
            @PathVariable(name = "id")String id,
            @RequestBody WeatherRequest request
    ) {
        CommonResponse<WeatherResponse> response = new CommonResponse();
        WeatherResponse resObj = weatherService.updateWeather(Long.parseLong(id), request);
        if(resObj == null){
            response.failedResponse("Data not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.successResponse("Data successfully updated!", resObj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/weather/{id}")
    public ResponseEntity<CommonResponse> removeWeather(@PathVariable(name = "id")String id){
        CommonResponse<WeatherResponse> response = new CommonResponse();
        WeatherResponse resObj = weatherService.removeWeather(Long.parseLong(id));
        if(resObj == null){
            response.failedResponse("Data not found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.successResponse("Data successfully removed!", resObj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}