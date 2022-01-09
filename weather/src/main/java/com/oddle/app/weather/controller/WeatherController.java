package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Map;

import com.oddle.app.weather.dto.BaseResponse;
import com.oddle.app.weather.dto.WeatherRequest;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.service.WeatherService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public ResponseEntity<BaseResponse> getWeatherByCity(@RequestParam("city") String city) {
        BaseResponse<WeatherResponse> baseRes = new BaseResponse<>();
        WeatherResponse res;
        try {
            res = service.getWeatherResponse(city);
        } catch (IOException e) {
            e.printStackTrace();
            baseRes.buildFailResponse("JSON structure of jsonData is unparseable.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseRes);
        }
        baseRes.buildSuccessResponse(res);
        return ResponseEntity.ok(baseRes);
    }
    
    @PostMapping(value = "/weather")
    public ResponseEntity<BaseResponse> saveWeather(@RequestBody WeatherRequest request) {
    	BaseResponse<WeatherResponse> baseRes = new BaseResponse<>();
        WeatherResponse res;
        try {
            res = service.saveWeather(request);
        } catch (IOException e) {
            e.printStackTrace();
            baseRes.buildFailResponse("JSON structure of jsonData is unparseable.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseRes);
        }

        baseRes.buildSuccessResponse(res);
        return ResponseEntity.ok(baseRes);
    }

    @GetMapping(value = "/weathers")
    public @ResponseBody ResponseEntity<BaseResponse> getWeatherHistory(@RequestParam(value = "fromDt", required = false) String fromDt, @RequestParam(value = "toDt", required = false) String toDt) {
    	BaseResponse<List<WeatherResponse>> baseRes = new BaseResponse<>();
    	LocalDateTime fromDateTime = LocalDateTime.now().minusDays(daysThreshold);
        LocalDateTime toDateTime = LocalDateTime.now();
        String statusMsg = service.isRequestValid(fromDt, toDt); 
        
        if(statusMsg.equalsIgnoreCase("OK")){
        	try {
            	fromDateTime = service.parseStringDateToLocalDateTime(fromDt);
            	toDateTime = service.parseStringDateToLocalDateTime(toDt);
    		} catch (DateTimeParseException e) {
    			e.printStackTrace();
    			baseRes.buildFailResponse("Date format must be yyyy-MM-dd");
    			return ResponseEntity.badRequest().body(baseRes);
    		}
            
            if(fromDateTime.isAfter(toDateTime)) {
            	baseRes.buildFailResponse("toDt must be after fromDt");
            	return ResponseEntity.badRequest().body(baseRes);
            }
        }else if(!statusMsg.equalsIgnoreCase("default")){
        	baseRes.buildFailResponse(statusMsg);
            return ResponseEntity.badRequest().body(baseRes);
        }
        
        List<WeatherResponse> list;
        try {
            list = service.getWeathersHistory(fromDateTime, toDateTime);
            if(list.isEmpty()){
            	baseRes.buildFailResponse("No history between dates.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseRes);
            } else {
            	baseRes.buildSuccessResponse(list);
                return ResponseEntity.ok(baseRes);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseRes);
        }
    }

    @DeleteMapping(value = "/weather/{id}")
    public ResponseEntity<BaseResponse> removeWeather(@PathVariable(value = "id", required = true) Long id) {
        if(id == null) {
        	BaseResponse<WeatherResponse> baseRes = new BaseResponse<>();
        	baseRes.buildFailResponse("id cannot be empty");
            return ResponseEntity.badRequest().body(baseRes);
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