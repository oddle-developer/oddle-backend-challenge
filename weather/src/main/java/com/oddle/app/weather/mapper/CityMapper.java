package com.oddle.app.weather.mapper;

import com.oddle.app.weather.domain.City;
import com.oddle.app.weather.dto.CityDTO;
import com.oddle.app.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper implements EntityDBToDTOMapper<City, CityDTO>{
    private final CityRepository repository;

    public CityMapper(CityRepository repository) {
        this.repository = repository;
    }


    @Override
    public CityDTO convertToDto(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setCityCode(city.getCityCode());
        cityDTO.setCityName(city.getCityName());
        cityDTO.setLattitude(city.getLattitude());
        cityDTO.setLongitude(city.getLongitude());

        return cityDTO;
    }

    @Override
    public List<CityDTO> convertToDto(List<City> cityList) {
        return cityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public City convertToEntity(CityDTO cityDTO) {
        City entity = new City();

        if (cityDTO.getId() != null) {
            entity = repository.findOne(cityDTO.getId());
        }

        entity.setCityCode(cityDTO.getCityCode());
        entity.setCityName(cityDTO.getCityName());
        entity.setLattitude(cityDTO.getLattitude());
        entity.setLongitude(cityDTO.getLongitude());

        return entity;
    }

    @Override
    public List<City> convertToEntity(List<CityDTO> cityDTOList) {
        return cityDTOList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
