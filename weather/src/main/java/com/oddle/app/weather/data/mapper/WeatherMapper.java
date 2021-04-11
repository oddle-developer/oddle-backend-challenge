package com.oddle.app.weather.data.mapper;

import com.oddle.app.weather.data.json.oddle.payload.AddRequest;
import com.oddle.app.weather.data.json.oddle.payload.WeatherResponse;
import com.oddle.app.weather.data.entity.City;
import com.oddle.app.weather.data.entity.Weather;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WeatherMapper {

    /**
     * This Instance is only used for Unit Testing and not for bean provider
     */
    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visibility", target = "visibility"),
            @Mapping(source = "windSpeed", target = "windSpeed"),
            @Mapping(target = "weather", ignore = true),
            @Mapping(target = "extend", ignore = true),
            @Mapping(target = "lastUpdate", ignore = true)
    })
    WeatherResponse mapEntityToResponse(Weather weatherEntity);

    @Mappings({
            @Mapping(source = "condition", target = "condition"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "tempMin", target = "tempMin"),
            @Mapping(source = "tempMax", target = "tempMax"),
            @Mapping(source = "humidity", target = "humidity"),
            @Mapping(source = "visibility", target = "visibility"),
            @Mapping(source = "windSpeed", target = "windSpeed"),
            @Mapping(target = "tempAvg", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
            @Mapping(source = "city", target = "city", ignore = true)
    })
    Weather mapRequestToEntity(AddRequest addRequest);

    @AfterMapping
    default void mapNestedProperty(Weather weatherEntity, @MappingTarget WeatherResponse weatherResponse) {
        final Map<String, Object> weatherJSON = new HashMap<>();
        final Map<String, Object> extendJSON = new HashMap<>();
        weatherJSON.put(WeatherResponse.JSON_WEATHER_CONDITION, weatherEntity.getCondition());
        weatherJSON.put(WeatherResponse.JSON_WEATHER_DESCRIPTION, weatherEntity.getDescription());
        weatherResponse.setWeather(weatherJSON);
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_AVG, weatherEntity.getTempAvg());
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_MIN, weatherEntity.getTempMin());
        extendJSON.put(WeatherResponse.JSON_EXTEND_TEMP_MAX, weatherEntity.getTempMax());
        extendJSON.put(WeatherResponse.JSON_EXTEND_HUMIDITY, weatherEntity.getHumidity());
        weatherResponse.setExtend(extendJSON);
    }

    @AfterMapping
    default void determineOtherProperties(AddRequest addRequest, @MappingTarget Weather weatherEntity) {
        double tempMax = addRequest.getTempMax();
        double tempMin = addRequest.getTempMin();
        weatherEntity.setTempAvg((tempMax + tempMin) / 2);
        Map<String, String> cityMap = addRequest.getCity();
        City city = new City();
        city.setName(cityMap.get(AddRequest.JSON_CITY_NAME));
        city.setLatitude(Double.parseDouble(Optional
                .ofNullable(cityMap.get(AddRequest.JSON_CITY_LATITUDE))
                .orElse("0")));
        city.setLongitude(Double.parseDouble(Optional
                .ofNullable(cityMap.get(AddRequest.JSON_CITY_LONGITUDE))
                .orElse("0")));
        weatherEntity.setCity(city);
    }
}
