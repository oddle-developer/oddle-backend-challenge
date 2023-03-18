package com.oddle.app.weather.service;

import com.oddle.app.weather.client.OpenWeatherClient;
import com.oddle.app.weather.dto.*;
import com.oddle.app.weather.dto.client.OpenWeatherGetWeatherResponseDto;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.repository.WeatherRepository;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class WeatherService {

    @Autowired
    OpenWeatherClient openWeatherClient;

    @Autowired
    WeatherRepository weatherRepository;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final String QUERY_PARAM_NAME="q";


    public GetWeatherByCityNameResponseDto getWeatherByCityName(String cityName){

        //This will map the city name into the request param hash map
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(QUERY_PARAM_NAME,cityName);

        //Get the weather data from open weather client
        OpenWeatherGetWeatherResponseDto responseDto = openWeatherClient.getWeatherByParams(requestMap);

        //This will map the open weather client response into API response
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        return mapperFacade.map(responseDto, GetWeatherByCityNameResponseDto.class);
    }

    public SaveWeatherDataResponseDto saveWeatherData(SaveWeatherDataRequestDto requestDto){

        //This will build the entity then save it to its table
        Weather weather = Weather.builder()
                .cityName(requestDto.getCityName())
                .weather(requestDto.getWeather())
                .temperature(requestDto.getTemperature())
                .humidity(requestDto.getHumidity())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        weatherRepository.save(weather);

        //This will map the request into API response
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        return mapperFacade.map(requestDto, SaveWeatherDataResponseDto.class);
    }

    public List<Weather> getWeatherData(GetWeatherDataRequestDto requestDto){

        try{
            if(requestDto.getCreatedAtStart() != null){
                return weatherRepository.findAllBetweenDates(requestDto.getCreatedAtStart(), requestDto.getCreatedAtEnd());
            }
        }catch(NullPointerException e){
            return weatherRepository.findAll();
        }

        return weatherRepository.findAll();
    }

}
