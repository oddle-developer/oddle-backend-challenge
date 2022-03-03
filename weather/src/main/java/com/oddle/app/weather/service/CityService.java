package com.oddle.app.weather.service;

import com.oddle.app.weather.pojo.dto.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> getAllCity();

    CityDTO getCityById(int cityId);

    CityDTO saveCity(CityDTO cityDto);

    CityDTO updateCity(int cityId, CityDTO cityDto);

    void deleteCity(int cityId);
}
