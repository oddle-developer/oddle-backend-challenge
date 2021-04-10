package com.oddle.app.weather.data.mapper;

import com.oddle.app.weather.data.entity.City;
import com.oddle.app.weather.data.entity.Weather;
import com.oddle.app.weather.data.json.openweather.payload.CurrentPayload;
import com.oddle.app.weather.data.json.openweather.node.CoordNode;
import com.oddle.app.weather.data.json.openweather.node.MainNode;
import com.oddle.app.weather.data.json.openweather.node.WeatherNode;
import com.oddle.app.weather.data.json.openweather.node.WindNode;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OpenWeatherMapper {

    OpenWeatherMapper INSTANCE = Mappers.getMapper(OpenWeatherMapper.class);

    @Mappings({
            @Mapping(source = "visibility", target = "visibility"),
            @Mapping(source = "weatherNode", target = "condition", qualifiedByName = "condition-qualifier"),
            @Mapping(source = "weatherNode", target = "description", qualifiedByName = "description-qualifier"),
            @Mapping(source = "mainNode", target = "tempAvg", qualifiedByName = "temp-avg-qualifier"),
            @Mapping(source = "mainNode", target = "tempMin", qualifiedByName = "temp-min-qualifier"),
            @Mapping(source = "mainNode", target = "tempMax", qualifiedByName = "temp-max-qualifier"),
            @Mapping(source = "mainNode", target = "humidity", qualifiedByName = "humidity-qualifier"),
            @Mapping(source = "windNode", target = "windSpeed", qualifiedByName = "wind-speed-qualifier"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
            @Mapping(target = "city", ignore = true)
    })
    Weather mapCurrentPayloadToWeather(CurrentPayload currentPayload);

    @Mappings({
            @Mapping(source = "cityName", target = "name"),
            @Mapping(source = "coordNode", target = "longitude", qualifiedByName = "longitude-qualifier"),
            @Mapping(source = "coordNode", target = "latitude", qualifiedByName = "latitude-qualifier"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
            @Mapping(target = "weathers", ignore = true)
    })
    City mapCurrentPayloadToCity(CurrentPayload currentPayload);

    @Named("temp-avg-qualifier")
    default double mapTempAvg(MainNode mainNode) {
        return mainNode.getTemp();
    }

    @Named("temp-min-qualifier")
    default double mapTempMin(MainNode mainNode) {
        return mainNode.getTempMin();
    }

    @Named("temp-max-qualifier")
    default double mapTempMax(MainNode mainNode) {
        return mainNode.getTempMax();
    }

    @Named("humidity-qualifier")
    default int mapHumidity(MainNode mainNode) {
        return mainNode.getHumidity();
    }

    @Named("condition-qualifier")
    default String mapCondition(List<WeatherNode> weatherNodes) {
        return mapWeatherModel(weatherNodes, WeatherNode::getMain);
    }

    @Named("description-qualifier")
    default String mapDescription(List<WeatherNode> weatherNodes) {
        return mapWeatherModel(weatherNodes, WeatherNode::getDescription);
    }

    default String mapWeatherModel(List<WeatherNode> weatherNodes, Function<WeatherNode, String> mapFunction) {
        if (weatherNodes.isEmpty()) {
            return null;
        }
        return Optional.ofNullable(weatherNodes.get(0))
                .map(mapFunction).orElse(null);
    }

    @Named("latitude-qualifier")
    default double mapLatitude(CoordNode coordNode) {
        return coordNode.getLat();
    }

    @Named("longitude-qualifier")
    default double mapLongitude(CoordNode coordNode) {
        return coordNode.getLon();
    }

    @Named("wind-speed-qualifier")
    default double mapWindSpeed(WindNode windNode) {
        return windNode.getSpeed();
    }
}
