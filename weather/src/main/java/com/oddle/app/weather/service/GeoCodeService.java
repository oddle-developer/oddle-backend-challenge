package com.oddle.app.weather.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.dto.GeoResponseDto;

public interface GeoCodeService {
    GeoResponseDto getLatLonByCity(String city) throws JsonProcessingException;
}
