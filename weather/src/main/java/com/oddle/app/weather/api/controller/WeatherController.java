package com.oddle.app.weather.api.controller;

import com.oddle.app.weather.dto.CityDTO;
import com.oddle.app.weather.dto.RequestWeatherHistoryDTO;
import com.oddle.app.weather.dto.ResponseDTO;
import com.oddle.app.weather.dto.WeatherHistoryDTO;
import com.oddle.app.weather.service.OpenWeatherService;
import com.oddle.app.weather.service.WeatherHistoryService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/*
* This is Controller which handling all of API for Weather activity.
* In general, all function on this controller are CRUD (Create, Update, Delete).
*
* So the prefix API for this controller is "weather" than will be followed by there fuction.
* Like add, update, delete, all and etc.
*
* All data will store in database while there's saving by ADD API, since only hit API get current weather
* the data will not store in database.
* */

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final OpenWeatherService openWeatherService;
    private final WeatherHistoryService weatherHistoryService;

    public WeatherController(OpenWeatherService openWeatherService, WeatherHistoryService weatherHistoryService) {
        this.openWeatherService = openWeatherService;
        this.weatherHistoryService = weatherHistoryService;
    }

    @GetMapping("")
    public ResponseDTO getWeathers() {
        return ResponseDTO.SUCCESS("Welcome to Oddle Backend Challenge");
    }

    @PostMapping(value = "/current")
    public ResponseDTO CreateOrUpdateWebOrder(@RequestBody CityDTO dto) {
        return ResponseDTO.SUCCESS(openWeatherService.getCurrentWeatherByCityName(dto.getCityName()));
    }

    @PostMapping(value = "/history/all")
    public ResponseDTO GetAllHistory(@RequestBody RequestWeatherHistoryDTO dto) {
        return ResponseDTO.SUCCESS((Serializable) weatherHistoryService.getAllHistory(dto));
    }

    @PostMapping(value = "/history/add")
    public ResponseDTO AddWeatherHistoryData(@RequestBody List<WeatherHistoryDTO> weatherHistoryDTOList) {
        return ResponseDTO.SUCCESS((Serializable) weatherHistoryService.addWeatherHistory(weatherHistoryDTOList));
    }

    @PutMapping(value = "/history/update")
    public ResponseDTO UpdateWeatherHistoryData(@RequestBody WeatherHistoryDTO weatherHistoryDTO) {
        return ResponseDTO.SUCCESS(weatherHistoryService.updateHistoryData(weatherHistoryDTO));
    }

    @DeleteMapping(value = "/history/delete/{id}")
    public ResponseDTO DeleteWeatherHistoryData(@PathVariable("id") String idInString) {
        weatherHistoryService.removeWeatherHistory(idInString);

        return ResponseDTO.SUCCESS("Success delete history data");
    }
}