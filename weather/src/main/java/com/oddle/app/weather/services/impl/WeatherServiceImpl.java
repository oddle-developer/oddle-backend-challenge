package com.oddle.app.weather.services.impl;

import com.oddle.app.weather.data.AddRequest;
import com.oddle.app.weather.data.WeatherResponse;
import com.oddle.app.weather.data.mapper.WeatherMapper;
import com.oddle.app.weather.exception.SaveOperationException;
import com.oddle.app.weather.model.City;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.repositories.CityRepository;
import com.oddle.app.weather.repositories.WeatherRepository;
import com.oddle.app.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.oddle.app.weather.util.TimeUtil.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    private final WeatherMapper weatherMapper;

    private final CityRepository cityRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, WeatherMapper weatherMapper, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone) {
        Pageable firstResult = PageRequest.of(0, 1);
        List<WeatherResponse> response = getWeatherResponse(cityName, timeZone, firstResult);
        if (response.size() == 0) {
            return null;
        }
        return Optional
                .ofNullable(response.get(0))
                .orElse(null);
    }

    @Override
    public List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone) {
        Pageable limitRecord = PageRequest.of(0, 20);
        return getWeatherResponse(cityName, timeZone, limitRecord);
    }

    @Override
    public List<WeatherResponse> getWeatherInRange(String cityName, LocalDate fromDate, LocalDate toDate, int page, TimeZone timeZone) {
        Pageable pageable = PageRequest.of(page, 30);
        return weatherRepository.findByCityInRangeDesc(
                cityName,
                getStartTimeOfDay(convertToUTCDate(fromDate, timeZone)),
                getEndTimeOfDay(convertToUTCDate(toDate, timeZone)),
                pageable
        ).stream()
                .map(weatherMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Weather addNewWeatherData(AddRequest addRequest) throws SaveOperationException {
        Weather weather = weatherMapper.mapRequestToEntity(addRequest);
        City city = weather.getCity();
        City inDbCity = cityRepository.findByName(city.getName()).orElseThrow(SaveOperationException::new);
        weather.setCity(inDbCity);
        return weatherRepository.save(weather);
    }

    private List<WeatherResponse> getWeatherResponse(String cityName, TimeZone timeZone, Pageable firstResult) {
        LocalDate todayAtUTC = convertToUTCDate(LocalDate.now(), timeZone);
        Date startOfDay = getStartTimeOfDay(todayAtUTC);
        Date endOfDay = getEndTimeOfDay(todayAtUTC);
        List<Weather> resultFromDB = weatherRepository.findByCityInRangeDesc(
                cityName,
                startOfDay,
                endOfDay,
                firstResult
        );
        return resultFromDB.stream()
                .map(weatherMapper::mapEntityToResponse)
                .collect(Collectors.toList());

    }

}
