package com.oddle.app.weather.controller;

import com.oddle.app.weather.data.json.oddle.payload.AddRequest;
import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.exception.FetchException;
import com.oddle.app.weather.exception.oddle.SaveOperationOddleFetchException;
import com.oddle.app.weather.services.WeatherFetchService;
import com.oddle.app.weather.services.WeatherService;
import com.oddle.app.weather.services.impl.OpenWeatherService;
import com.oddle.app.weather.services.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    private final WeatherFetchService openWeatherService;

    @Autowired
    public WeatherController(WeatherService weatherService,
                             @Qualifier("open-weather-service") WeatherFetchService openWeatherService) {
        this.weatherService = weatherService;
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponse> getCurrentWeatherFrom(@RequestParam("city") String cityName,
                                                                 TimeZone timeZone) throws FetchException {
        WeatherResponse response = weatherService.getCurrentWeather(cityName, timeZone);
        if (response == null) {
            response = openWeatherService.getCurrentWeather(cityName, timeZone);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/history", params = {"city"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WeatherResponse>> getHistoricalWeatherFrom(@RequestParam("city") String cityName,
                                                                          TimeZone timeZone) throws FetchException {
        List<WeatherResponse> response = weatherService.getHistoricalWeather(cityName, timeZone);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WeatherResponse>> getWeatherInRangeFrom(@RequestParam("city") String cityName,
                                                                       @RequestParam("from") LocalDate fromDate,
                                                                       @RequestParam("to") LocalDate toDate,
                                                                       @RequestParam("page") int page,
                                                                       TimeZone timeZone) {
        List<WeatherResponse> response = weatherService.getWeatherInRange(cityName, fromDate, toDate, page, timeZone);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addWeather(AddRequest addRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            String addedId = weatherService.addNewWeatherData(addRequest);
            response.put("message", "New Weather Data Added");
            response.put("weather_id", addedId);
        } catch (SaveOperationOddleFetchException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
