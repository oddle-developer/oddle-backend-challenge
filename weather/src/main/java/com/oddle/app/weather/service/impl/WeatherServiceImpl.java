package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.exception.WeatherNotFoundException;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.pojo.dto.WeatherDTO;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WeatherServiceImpl extends BaseService implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<WeatherDTO> getAllWeather() {
        List<Weather> weathers = weatherRepository.findAll();
        return convertObject(weathers, WeatherDTO.class);
    }

    @Override
    public WeatherDTO getWeatherById(int weatherId) {
        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() ->
                        new WeatherNotFoundException("id " + weatherId + " not found"));
        return convertObject(weather, WeatherDTO.class);
    }

    @Override
    public WeatherDTO saveWeather(WeatherDTO weatherDto) {
        Weather weather = convertObject(weatherDto, Weather.class);
        weather = weatherRepository.save(weather);
        return convertObject(weather, WeatherDTO.class);
    }

    @Override
    public WeatherDTO updateWeather(int weatherId, WeatherDTO weatherDto) {
        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() ->
                        new WeatherNotFoundException("id " + weatherId + " not found"));
        weather.setDescription(weatherDto.getDescription());
        weather.setMain(weatherDto.getMain());
        weather = weatherRepository.save(weather);
        return convertObject(weather, WeatherDTO.class);
    }

    @Override
    public void deleteWeather(int weatherId) {
        Weather weather = weatherRepository.findById(weatherId)
                .orElseThrow(() ->
                        new WeatherNotFoundException("id " + weatherId + " not found"));
        weatherRepository.delete(weather);
    }
}
