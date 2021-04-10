package com.oddle.app.weather.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.data.entity.City;
import com.oddle.app.weather.data.entity.Weather;
import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.data.json.openweather.payload.CurrentPayload;
import com.oddle.app.weather.data.mapper.OpenWeatherMapper;
import com.oddle.app.weather.data.mapper.WeatherMapper;
import com.oddle.app.weather.exception.FetchException;
import com.oddle.app.weather.exception.openweather.OpenWeatherFetchException;
import com.oddle.app.weather.repositories.CityRepository;
import com.oddle.app.weather.repositories.WeatherRepository;
import com.oddle.app.weather.services.WeatherFetchService;
import com.oddle.app.weather.client.OpenWeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimeZone;

@Service(value = OpenWeatherService.QUALIFIER_NAME)
public class OpenWeatherService implements WeatherFetchService {

    public static final String QUALIFIER_NAME = "open-weather-service";

    private final OpenWeatherClient openWeatherClient;

    private final OpenWeatherMapper openWeatherMapper;

    private final WeatherMapper weatherMapper;

    private final CityRepository cityRepository;

    private final WeatherRepository weatherRepository;

    @Autowired
    public OpenWeatherService(OpenWeatherClient openWeatherClient,
                              OpenWeatherMapper openWeatherMapper,
                              WeatherMapper weatherMapper,
                              CityRepository cityRepository,
                              WeatherRepository weatherRepository) {
        this.openWeatherClient = openWeatherClient;
        this.openWeatherMapper = openWeatherMapper;
        this.weatherMapper = weatherMapper;
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone) throws FetchException {
        WeatherResponse response;
        CurrentPayload payload;
        try {
            payload = openWeatherClient.fetchCurrentWeather(cityName);
        } catch (JsonProcessingException e) {
            throw new OpenWeatherFetchException(e);
        }
        assert payload != null;
        Weather weatherEntity = openWeatherMapper.mapCurrentPayloadToWeather(payload);
        City city = cityRepository.findByNameIgnoreCase(payload.getCityName()).orElse(
                openWeatherMapper.mapCurrentPayloadToCity(payload)
        );
        weatherEntity.setCity(city);
        Weather savedToDB = weatherRepository.save(weatherEntity);
        response = weatherMapper.mapEntityToResponse(savedToDB);
        return response;
    }

    @Override
    public List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone) {
        return null;
    }

}
