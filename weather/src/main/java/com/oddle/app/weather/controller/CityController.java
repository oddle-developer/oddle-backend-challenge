package com.oddle.app.weather.controller;

import com.oddle.app.weather.pojo.dto.CityDTO;
import com.oddle.app.weather.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/cities")
    public List<CityDTO> getAllCity() {
        return cityService.getAllCity();
    }

    @GetMapping("/cities/{cityId}")
    public CityDTO getCityById(@PathVariable("cityId") int cityId) {
        return cityService.getCityById(cityId);
    }

    @PostMapping("/cities")
    public CityDTO saveCity(@RequestBody CityDTO cityDto) {
        return cityService.saveCity(cityDto);
    }

    @PutMapping("/cities/{cityId}")
    public CityDTO updateCity(@PathVariable("cityId") int cityId, @RequestBody CityDTO cityDto) {
        return cityService.updateCity(cityId, cityDto);
    }

    @DeleteMapping("/cities/{cityId}")
    public String deleteCity(@PathVariable("cityId") int cityId) {
        cityService.deleteCity(cityId);
        return "Deleted Successfully";
    }
}
