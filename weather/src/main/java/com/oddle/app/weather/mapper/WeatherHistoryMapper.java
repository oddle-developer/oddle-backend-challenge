package com.oddle.app.weather.mapper;

import com.oddle.app.weather.domain.WeatherHistory;
import com.oddle.app.weather.dto.WeatherHistoryDTO;
import com.oddle.app.weather.repository.WeatherHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.oddle.app.weather.util.ObjectUtil.isExist;

@Component
public class WeatherHistoryMapper implements EntityDBToDTOMapper<WeatherHistory, WeatherHistoryDTO>{
    private final WeatherHistoryRepository weatherHistoryRepository;


    public WeatherHistoryMapper(WeatherHistoryRepository weatherHistoryRepository) {
        this.weatherHistoryRepository = weatherHistoryRepository;
    }


    @Override
    public WeatherHistoryDTO convertToDto(WeatherHistory weatherHistory) {
        WeatherHistoryDTO weatherHistoryDTO = new WeatherHistoryDTO();
        weatherHistoryDTO.setId(weatherHistory.getId());
        weatherHistoryDTO.setCityCode(weatherHistory.getCityCode());
        weatherHistoryDTO.setCityName(weatherHistory.getCityName());
        weatherHistoryDTO.setMainWeather(weatherHistory.getMainWeather());
        weatherHistoryDTO.setDetailWeather(weatherHistory.getDetailWeather());
        weatherHistoryDTO.setWeatherIcon(weatherHistory.getWeatherIcon());
        weatherHistoryDTO.setLattitude(weatherHistory.getLattitude());
        weatherHistoryDTO.setLongitude(weatherHistory.getLongitude());
        weatherHistoryDTO.setDateAdded(weatherHistory.getDateAdded());
        weatherHistoryDTO.setDateModified(weatherHistory.getDateModified());
        weatherHistoryDTO.setDeleted(weatherHistory.isDeleted());

        return weatherHistoryDTO;
    }

    @Override
    public List<WeatherHistoryDTO> convertToDto(List<WeatherHistory> weatherHistoryList) {
        return weatherHistoryList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public WeatherHistory convertToEntity(WeatherHistoryDTO weatherHistoryDTO) {
        WeatherHistory weatherHistory = new WeatherHistory();

        if(isExist(weatherHistoryDTO.getId()))
            weatherHistory = weatherHistoryRepository.findOne(weatherHistoryDTO.getId());

        weatherHistory.setCityCode(weatherHistoryDTO.getCityCode());
        weatherHistory.setCityName(weatherHistoryDTO.getCityName());
        weatherHistory.setMainWeather(weatherHistoryDTO.getMainWeather());
        weatherHistory.setDetailWeather(weatherHistoryDTO.getDetailWeather());
        weatherHistory.setWeatherIcon(weatherHistoryDTO.getWeatherIcon());
        weatherHistory.setLattitude(weatherHistoryDTO.getLattitude());
        weatherHistory.setLongitude(weatherHistoryDTO.getLongitude());
        weatherHistory.setDateAdded(weatherHistoryDTO.getDateAdded());
        weatherHistory.setDateModified(weatherHistoryDTO.getDateModified());
        weatherHistory.setDeleted(weatherHistoryDTO.isDeleted());

        return weatherHistory;
    }

    @Override
    public List<WeatherHistory> convertToEntity(List<WeatherHistoryDTO> weatherHistoryDTOList) {
        return weatherHistoryDTOList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
