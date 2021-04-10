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
import com.oddle.app.weather.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.oddle.app.weather.util.TimeUtil.*;

@Service
@Qualifier(value = WeatherServiceImpl.QUALIFIER_NAME)
public class WeatherServiceImpl implements WeatherService {

    public static final String QUALIFIER_NAME = "oddle-service";

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
        List<Weather> response = getRecentWeatherData(cityName,
                timeZone,
                firstResult,
                requestTime -> requestTime.minusMinutes(30));
        if (response.size() == 0) {
            return null;
        }
        return Optional
                .ofNullable(response.get(0))
                .map(weatherMapper::mapEntityToResponse)
                .orElse(null);
    }

    @Override
    public List<WeatherResponse> getHistoricalWeather(String cityName, TimeZone timeZone) throws FetchException {
        Pageable limitRecord = PageRequest.of(0, 20);
        return getRecentWeatherData(cityName,
                timeZone,
                limitRecord,
                requestTime -> requestTime.minusDays(7)).stream()
                .map(entity -> {
                    WeatherResponse response = weatherMapper.mapEntityToResponse(entity);
                    response.setLastUpdate(TimeUtil.convertToCurrentTimeZone(
                            entity.getUpdateTime(), timeZone
                    ));
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<WeatherResponse> getWeatherInRange(String cityName,
                                                   LocalDate fromDate,
                                                   LocalDate toDate,
                                                   int page,
                                                   TimeZone timeZone) {
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
        if (city == null) {
            throw new SaveOperationOddleFetchException();
        }
        City inDbCity = cityRepository
                .findByNameIgnoreCase(city.getName())
                .orElse(city);
        weather.setCity(inDbCity);
        return weatherRepository.save(weather).getId();
    }

    private List<Weather> getRecentWeatherData(String cityName,
                                               TimeZone timeZone,
                                               Pageable limitRecord,
                                               Function<LocalDateTime, LocalDateTime> refreshFunction) throws OddleFetchException {
        LocalDateTime requestTime = convertToUTCTime(LocalDateTime.now(), timeZone);
        try {
            return weatherRepository.findByCityInRangeDesc(
                    cityName,
                    Timestamp.valueOf(refreshFunction.apply(requestTime)),
                    Timestamp.valueOf(requestTime),
                    limitRecord
            );
        } catch (Exception e) {
            throw new OddleFetchException(e);
        }

    }
}
