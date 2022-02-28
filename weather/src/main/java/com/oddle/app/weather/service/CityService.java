package com.oddle.app.weather.service;

import com.oddle.app.weather.domain.City;
import com.oddle.app.weather.dto.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> getAllCity();
    CityDTO getCityByParam(CityDTO cityDTO);
    List<CityDTO> addCity(List<CityDTO> cityDTOList);
    CityDTO updateCityData(CityDTO cityDTO);
    void removeCity(Long id);
}
