package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.api.exception.ErrorApp;
import com.oddle.app.weather.domain.City;
import com.oddle.app.weather.domain.WeatherHistory;
import com.oddle.app.weather.dto.RequestWeatherHistoryDTO;
import com.oddle.app.weather.dto.WeatherHistoryDTO;
import com.oddle.app.weather.mapper.WeatherHistoryMapper;
import com.oddle.app.weather.repository.WeatherHistoryRepository;
import com.oddle.app.weather.service.WeatherHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.oddle.app.weather.util.ObjectUtil.isExist;

@Slf4j
@Service
public class WeatherHistoryServiceImpl implements WeatherHistoryService {
    private final WeatherHistoryRepository weatherHistoryRepository;
    private final WeatherHistoryMapper weatherHistoryMapper;

    public WeatherHistoryServiceImpl(WeatherHistoryRepository weatherHistoryRepository, WeatherHistoryMapper weatherHistoryMapper) {
        this.weatherHistoryRepository = weatherHistoryRepository;
        this.weatherHistoryMapper = weatherHistoryMapper;
    }

    @Override
    public List<WeatherHistoryDTO> getAllHistory(RequestWeatherHistoryDTO dto) {
        DateFormat dateFormatRequest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WeatherHistory> weatherHistoryListFromDB = new ArrayList<>();

        try{
            if(isExist(dto.getDateStart()) && !isExist(dto.getDateEnd())){
                Date dateStart = dateFormatRequest.parse(dto.getDateStart());
                weatherHistoryListFromDB = weatherHistoryRepository.findAllByDeletedAndDateModifiedAfter(false, dateStart);
            } else if(isExist(dto.getDateStart()) && isExist(dto.getDateEnd())){
                Date dateStart = dateFormatRequest.parse(dto.getDateStart());
                Date dateEnd = dateFormatRequest.parse(dto.getDateEnd());
                weatherHistoryListFromDB = weatherHistoryRepository.findAllByDeletedAndDateModifiedBetween(false, dateStart, dateEnd);
            } else {
                weatherHistoryListFromDB = weatherHistoryRepository.findAllByDeleted(false);
            }
        } catch (Exception ex){
            log.error("Error parsing date start or date end with message " + ex.getMessage());
        }

        return weatherHistoryMapper.convertToDto(weatherHistoryListFromDB);
    }

    @Override
    public WeatherHistoryDTO getHistoryByParam(WeatherHistoryDTO weatherHistoryDTO) {
        WeatherHistory weatherHistory = new WeatherHistory();
        if(isExist(weatherHistoryDTO.getId()))
            weatherHistory = weatherHistoryRepository.findOne(weatherHistoryDTO.getId());

        if(isExist(weatherHistoryDTO.getCityCode()))
            weatherHistory = weatherHistoryRepository.findByCityCode(weatherHistoryDTO.getCityCode());

        if(isExist(weatherHistoryDTO.getCityName()))
            weatherHistory = weatherHistoryRepository.findByCityNameLike(weatherHistoryDTO.getCityName()).get(0);

        return weatherHistoryMapper.convertToDto(weatherHistory);
    }

    @Override
    public List<WeatherHistoryDTO> addWeatherHistory(List<WeatherHistoryDTO> weatherHistoryDTOList) {
        List<WeatherHistoryDTO> savedHistoryDataList = new ArrayList<>();
        for (WeatherHistoryDTO historyRequest : weatherHistoryDTOList) {
            WeatherHistory historyToSave = weatherHistoryMapper.convertToEntity(historyRequest);

            Date dtmToday = new Date();
            historyToSave.setDateAdded(dtmToday);
            historyToSave.setDateModified(dtmToday);
            historyToSave.setDeleted(false);

            WeatherHistory savedData = weatherHistoryRepository.save(historyToSave);

            savedHistoryDataList.add(weatherHistoryMapper.convertToDto(savedData));
        }
        return savedHistoryDataList;
    }

    @Override
    public WeatherHistoryDTO updateHistoryData(WeatherHistoryDTO weatherHistoryDTO) {
        WeatherHistory savedWeatherHistory = weatherHistoryRepository.findOne(weatherHistoryDTO.getId());

        if(!isExist(savedWeatherHistory))
            throw new ErrorApp("400", "Data with ID " + weatherHistoryDTO.getId() +" Not found");

        if(isExist(weatherHistoryDTO.getCityCode()))
            savedWeatherHistory.setCityCode(weatherHistoryDTO.getCityCode());

        if(isExist(weatherHistoryDTO.getCityName()))
            savedWeatherHistory.setCityName(weatherHistoryDTO.getCityName());

        if(isExist(weatherHistoryDTO.getMainWeather()))
            savedWeatherHistory.setMainWeather(weatherHistoryDTO.getMainWeather());

        if(isExist(weatherHistoryDTO.getDetailWeather()))
            savedWeatherHistory.setDetailWeather(weatherHistoryDTO.getDetailWeather());

        if(isExist(weatherHistoryDTO.getWeatherIcon()))
            savedWeatherHistory.setWeatherIcon(weatherHistoryDTO.getWeatherIcon());

        savedWeatherHistory.setDateModified(new Date());

        savedWeatherHistory = weatherHistoryRepository.save(savedWeatherHistory);
        return weatherHistoryMapper.convertToDto(savedWeatherHistory);
    }

    @Override
    public void removeWeatherHistory(String id) {
        WeatherHistory weatherHistory = weatherHistoryRepository.findOne(id);

        if(!isExist(weatherHistory))
            throw new ErrorApp("400", "Data with ID " + id +" Not found");


        weatherHistory.setDeleted(true);
        weatherHistoryRepository.save(weatherHistory);
    }
}
