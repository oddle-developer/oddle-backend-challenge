package com.oddle.app.weather.data.mapper;

import com.oddle.app.weather.data.WeatherResponse;
import com.oddle.app.weather.model.Weather;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visibility", target = "visibility"),
            @Mapping(source = "windSpeed", target = "windSpeed"),
            @Mapping(target = "weather", ignore = true),
            @Mapping(target = "extend", ignore = true)
    })
    WeatherResponse mapEntityToResponse(Weather weatherEntity);

    @AfterMapping
    default void mapNestedProperty(Weather weatherEntity, @MappingTarget WeatherResponse weatherResponse) {
        final Map<String, Object> weatherJSON = new LinkedHashMap<>();
        final Map<String, Object> extendJSON = new LinkedHashMap<>();
        weatherJSON.put(WeatherResponse.JSON_WEATHER_CONDITION, weatherEntity.getWeatherCondition());
        weatherJSON.put(WeatherResponse.JSON_WEATHER_DESCRIPTION, weatherEntity.getConditionDescription());
        weatherResponse.setWeather(weatherJSON);
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_AVG, weatherEntity.getTempAvg());
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_MIN, weatherEntity.getTempMin());
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_MAX, weatherEntity.getTempMax());
        extendJSON.put(WeatherResponse.JSON_EXTEND_HUMIDITY, weatherEntity.getHumidity());
        weatherResponse.setExtend(extendJSON);
    }
}
