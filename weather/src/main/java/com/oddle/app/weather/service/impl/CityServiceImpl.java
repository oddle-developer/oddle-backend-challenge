package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.api.exception.ErrorApp;
import com.oddle.app.weather.domain.City;
import com.oddle.app.weather.dto.CityDTO;
import com.oddle.app.weather.mapper.CityMapper;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.oddle.app.weather.util.ObjectUtil.isExist;

@Slf4j
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    @Override
    public List<CityDTO> getAllCity() {
        List<City> listCityFromDB = cityRepository.findAll();
        return cityMapper.convertToDto(listCityFromDB);
    }

    @Override
    public CityDTO getCityByParam(CityDTO cityDTO) {
        City city = new City();
        if(isExist(cityDTO.getId()))
            city = cityRepository.findOne(cityDTO.getId());

        if(isExist(cityDTO.getCityCode()))
            city = cityRepository.findByCityCode(cityDTO.getCityCode());

        if(isExist(cityDTO.getCityName())){
            List<City> resultCity = cityRepository.findByCityNameLike(cityDTO.getCityName());

            if(resultCity.size() > 0)
                city = resultCity.get(0);
            else
                throw new ErrorApp("400", "City not found");
        }

        return cityMapper.convertToDto(city);
    }

    @Override
    public List<CityDTO> addCity(List<CityDTO> cityDTOList) {
        List<CityDTO> savedCityDataList = new ArrayList<>();
        for (CityDTO cityDataFromRequest : cityDTOList) {
            City cityDataToSave = cityMapper.convertToEntity(cityDataFromRequest);

            City savedData = cityRepository.save(cityDataToSave);

            savedCityDataList.add(cityMapper.convertToDto(savedData));
        }
        return savedCityDataList;
    }

    @Override
    public CityDTO updateCityData(CityDTO cityDTO) {
        City savedCityData = cityRepository.findOne(cityDTO.getId());

        if(!isExist(savedCityData))
            throw new ErrorApp("400", "Data with ID " + savedCityData.getId() +" Not found");

        if(isExist(cityDTO.getCityCode()))
            savedCityData.setCityCode(cityDTO.getCityCode());

        if(isExist(cityDTO.getCityName()))
            savedCityData.setCityName(cityDTO.getCityName());

        if(isExist(cityDTO.getLattitude()) && cityDTO.getLattitude() != 0)
            savedCityData.setLattitude(cityDTO.getLattitude());

        if(isExist(cityDTO.getLongitude()) && cityDTO.getLongitude() != 0)
            savedCityData.setLongitude(cityDTO.getLongitude());

        savedCityData = cityRepository.save(savedCityData);
        return cityMapper.convertToDto(savedCityData);
    }

    @Override
    public void removeCity(Long id) {
        City cityDataToRemove = cityRepository.findOne(id);
        if(!isExist(cityDataToRemove))
            throw new ErrorApp("400", "Data with ID " + id +" Not found");

        cityRepository.delete(cityDataToRemove);
    }
}
