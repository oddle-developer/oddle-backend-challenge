package com.oddle.app.weather.service;


import com.oddle.app.weather.dto.RequestWeatherHistoryDTO;
import com.oddle.app.weather.dto.WeatherHistoryDTO;

import java.util.List;

public interface WeatherHistoryService {
    List<WeatherHistoryDTO> getAllHistory(RequestWeatherHistoryDTO dto);
    WeatherHistoryDTO getHistoryByParam(WeatherHistoryDTO weatherHistoryDTO);
    List<WeatherHistoryDTO> addWeatherHistory(List<WeatherHistoryDTO> weatherHistoryDTOList);
    WeatherHistoryDTO updateHistoryData(WeatherHistoryDTO weatherHistoryDTO);
    void removeWeatherHistory(String id);
}
