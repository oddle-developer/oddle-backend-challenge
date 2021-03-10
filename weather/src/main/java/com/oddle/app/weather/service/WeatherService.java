package com.oddle.app.weather.service;

import com.oddle.app.weather.exception.WeatherNotFoundException;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // save weather
    public Weather saveWeatherService(Weather weather) {
        weather.setAccessedAt(LocalDateTime.now());
        return weatherRepository.save(weather);
    }

    // find weather history
    public Weather findWeatherHistoryById(Long id) {
        return weatherRepository.findWeatherById(id).
                orElseThrow(() -> new WeatherNotFoundException("Weather history with id " + id + " doesn't exist"));
    }

    // list weather history
    public List<Weather> listWeatherHistory() {
        return weatherRepository.findAll();
    }

    // update weather history
    public Weather updateWeatherHistory(Long id, Weather weather) {

        Weather weatherHistory = findWeatherHistoryById(id);
        weatherHistory.setCityName(weather.getCityName());
        weatherHistory.setTemperature(weather.getTemperature());
        weatherHistory.setMainWeather(weather.getMainWeather());
        weatherHistory.setDescWeather(weather.getDescWeather());

        return weatherRepository.save(weatherHistory);
    }

    // delete weather history
    public void deleteWeatherHistory(Long id) {
        weatherRepository.deleteById(id);
    }

}
