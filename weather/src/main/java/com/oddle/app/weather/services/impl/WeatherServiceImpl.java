package com.oddle.app.weather.services.impl;

import com.oddle.app.weather.data.entity.City;
import com.oddle.app.weather.data.entity.Weather;
import com.oddle.app.weather.data.json.oddle.payload.AddRequest;
import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.data.mapper.WeatherMapper;
import com.oddle.app.weather.exception.FetchException;
import com.oddle.app.weather.exception.oddle.OddleFetchException;
import com.oddle.app.weather.exception.oddle.SaveOperationOddleFetchException;
import com.oddle.app.weather.repositories.CityRepository;
import com.oddle.app.weather.repositories.WeatherRepository;
import com.oddle.app.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
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
    public WeatherResponse getCurrentWeather(String cityName, TimeZone timeZone) throws FetchException {
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
    public List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone) throws FetchException {
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
    public String addNewWeatherData(AddRequest addRequest) throws SaveOperationOddleFetchException {
        Weather weather = weatherMapper.mapRequestToEntity(addRequest);
        City city = weather.getCity();
        City inDbCity = cityRepository.findByNameIgnoreCase(city.getName()).orElseThrow(SaveOperationOddleFetchException::new);
        weather.setCity(inDbCity);
        return weatherRepository.save(weather).getId();
    }

    private List<WeatherResponse> getWeatherResponse(String cityName,
                                                     TimeZone timeZone,
                                                     Pageable firstResult) throws FetchException {
        LocalDate todayAtUTC = convertToUTCDate(LocalDate.now(), timeZone);
        Date startOfDay = getStartTimeOfDay(todayAtUTC);
        Date endOfDay = getEndTimeOfDay(todayAtUTC);
        List<Weather> resultFromDB;
        try {
            resultFromDB = weatherRepository.findByCityInRangeDesc(
                    cityName,
                    startOfDay,
                    endOfDay,
                    firstResult
            );
        } catch (RuntimeException e) {
            throw new OddleFetchException(e);
        }
        return resultFromDB.stream()
                .map(weatherMapper::mapEntityToResponse)
                .collect(Collectors.toList());

    }

}
