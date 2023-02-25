package com.oddle.app.weather.service;

import com.oddle.app.weather.model.internalmapper.City;
import com.oddle.app.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }
}
