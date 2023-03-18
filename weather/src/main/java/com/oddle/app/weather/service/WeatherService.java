package com.oddle.app.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.client.OpenWeatherClient;
import com.oddle.app.weather.dto.GetWeatherByParamsRequestDto;
import com.oddle.app.weather.dto.GetWeatherResponseDto;
import com.oddle.app.weather.dto.client.OpenWeatherGetWeatherResponseDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WeatherService {

    @Autowired
    OpenWeatherClient openWeatherClient;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final String QUERY_PARAM_NAME="q";


    public GetWeatherResponseDto getWeatherByCityName(String cityName){

        //This will map the city name into the request param hash map
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(QUERY_PARAM_NAME,cityName);

        //Get the weather data from open weather client
        OpenWeatherGetWeatherResponseDto responseDto = openWeatherClient.getWeatherByParams(requestMap);

        //This will map the open weather client response into API response
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        return mapperFacade.map(responseDto,GetWeatherResponseDto.class);
    }

}
