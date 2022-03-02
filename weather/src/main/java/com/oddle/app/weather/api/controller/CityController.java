package com.oddle.app.weather.api.controller;

import com.oddle.app.weather.dto.CityDTO;
import com.oddle.app.weather.dto.ResponseDTO;
import com.oddle.app.weather.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;


/*
 * This is Controller which handling all of API for City master data.
 * In general, all function on this controller are CRUD (Create, Update, Delete).
 *
 * So the prefix API for this controller is "city" than will be followed by there fuction.
 * Like add, update, delete, all and etc.
 *
 * We need this master data API because we need to parse "City Name" to point Lat and Long,
 * this is pre request needed for API weather, when we want to use global place database like google
 * we can use their API.
 * But for this project using local city database.
 *
 *
 * */

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;


    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseDTO getWeathers() {
        return ResponseDTO.SUCCESS((Serializable) cityService.getAllCity());
    }

    @PostMapping("/add")
    public ResponseDTO addCity(@RequestBody List<CityDTO> dtoList) {
        return ResponseDTO.SUCCESS((Serializable) cityService.addCity(dtoList));
    }

    @PutMapping("/update")
    public ResponseDTO updateCity(@RequestBody CityDTO dto) {
        return ResponseDTO.SUCCESS(cityService.updateCityData(dto));
    }

    /*
    * For API delete city, the request need to encode ID using simple Base64 url.
    * */
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteCity(@PathVariable("id") String idInString) {
        String paramEncoded = new String(Base64.getUrlDecoder().decode(idInString.getBytes()));
        cityService.removeCity(Long.valueOf(paramEncoded));
        return ResponseDTO.SUCCESS("Success to delete data");
    }
}
