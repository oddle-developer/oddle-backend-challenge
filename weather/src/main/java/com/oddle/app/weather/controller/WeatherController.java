package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.dto.request.weather.save.EditWeatherDataRequest;
import com.oddle.app.weather.dto.request.weather.save.SaveWeatherDataRequest;
import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.dto.response.Response;
import com.oddle.app.weather.exception.CurrentCityWeatherException;
import com.oddle.app.weather.exception.DateException;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.service.CityWeatherService;
import com.oddle.app.weather.service.WeatherService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class WeatherController {
	
	@Autowired
	@Qualifier("OpenWeatherService")
	private CityWeatherService cityWeatherService;
	
	@Autowired
	@Qualifier("OpenWeatherService")
	private WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }
    
    @GetMapping("/current")
    public Response<WeatherResponse> getCurrentWeather(@RequestParam("city") String city) throws CurrentCityWeatherException{
    	return Response.success(cityWeatherService.getCurrentWeather(city));
    }
    
    @GetMapping("/history")
    public Response<Page<WeatherResponse>> getWeatherHistory(
    		@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fromDate,
    		@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate toDate,
    		@RequestParam(value = "cityName", required=false) String city,
    		@RequestParam("page") Optional<Integer> page,
    		@RequestParam("size") Optional<Integer> size
    		) {
    	
        return Response.success(weatherService.history(city,fromDate, toDate,page.orElseGet(()->0),size.orElseGet(()->10)));
    }
    
    @PostMapping("/save")
    public Response<?> save(@RequestBody @Valid SaveWeatherDataRequest saveWeatherDataRequest) throws DateException{
    	weatherService.save(saveWeatherDataRequest);
    	return Response.success(HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
    }
    
    @PutMapping("/edit")
    public Response<?> edit(@RequestBody EditWeatherDataRequest editWeatherDataRequest) throws DateException, WeatherException{
    	weatherService.edit(editWeatherDataRequest);
    	return Response.success(HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
    }
    
    @DeleteMapping("/delete/{id}")
    public Response<?> delete(@PathVariable Long id) throws WeatherException{
    	weatherService.delete(id);
    	return Response.success(HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
    }
}