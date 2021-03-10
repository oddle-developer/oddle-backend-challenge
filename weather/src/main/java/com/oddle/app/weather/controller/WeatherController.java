package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    private static final String KEY_ = "95227e300847d653aba11e445c2dfab3";

    // GET api status
    @GetMapping("/status")
    public ResponseEntity<String> getApiStatus() {
        String msg = "This weather API is working..";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    // GET weather current
    @GetMapping("/current/{city}")
    public ResponseEntity<Object> getWeatherCurrent(@PathVariable(value = "city") String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + KEY_;
        Object dataResponse = restTemplate.getForObject(url, Object.class);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    // GET weather history
    @GetMapping("/list")
    public ResponseEntity<List<Weather>> getWeatherHistory() {
        List<Weather> weatherHistory = weatherService.listWeatherHistory();
        return new ResponseEntity<>(weatherHistory, HttpStatus.OK);
    }

    // POST weather
    @PostMapping("/save")
    public ResponseEntity<Weather> postWeather(@RequestBody Weather weather) {
        Weather current = weatherService.saveWeatherService(weather);
        return new ResponseEntity<>(current, HttpStatus.CREATED);
    }

    // PUT weather
    @PutMapping("/update/{id}")
    public ResponseEntity<Weather> putWeather(@PathVariable(value = "id") Long id, @RequestBody Weather weather) {
        Weather updatedWeather = weatherService.updateWeatherHistory(id, weather);
        return new ResponseEntity<>(updatedWeather, HttpStatus.OK);
    }

    // DELETE weather history
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWeatherHistory(@PathVariable("id") Long id) {
        weatherService.deleteWeatherHistory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
