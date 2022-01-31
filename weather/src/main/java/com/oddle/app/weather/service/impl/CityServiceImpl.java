package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.exception.CityNotFoundException;
import com.oddle.app.weather.model.City;
import com.oddle.app.weather.pojo.dto.CityDTO;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl extends BaseService implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CityDTO> getAllCity() {
        List<City> cities = cityRepository.findAll();
        return convertObject(cities, CityDTO.class);
    }

    @Override
    public CityDTO getCityById(int cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() ->
                        new CityNotFoundException("id " + cityId + "not found"));
        return convertObject(city, CityDTO.class);
    }

    @Override
    public CityDTO saveCity(CityDTO cityDto) {
        City city = convertObject(cityDto, City.class);
        city = cityRepository.save(city);
        return convertObject(city, CityDTO.class);
    }

    @Override
    public CityDTO updateCity(int cityId, CityDTO cityDto) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() ->
                        new CityNotFoundException("id " + cityId + "not found"));
        city.setCityName(cityDto.getCityName());
        city.setCountry(cityDto.getCountry());
        city.setLongitude(cityDto.getLongitude());
        city.setLatitude(cityDto.getLatitude());
        city = cityRepository.save(city);
        return convertObject(city, CityDTO.class);
    }

    @Override
    public void deleteCity(int cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() ->
                        new CityNotFoundException("id " + cityId + "not found"));
        cityRepository.delete(city);
    }
}
