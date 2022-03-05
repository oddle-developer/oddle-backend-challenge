package com.oddle.app.weather.mapper;

import com.oddle.app.weather.dto.HistoricalDto;
import com.oddle.app.weather.entity.Weather;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    public Weather weatherResponseToEntity(String city, HistoricalDto historicalDto){
        return Weather.builder()
                .city(city)
                .weatherDescription(historicalDto.getWeather())
                .date(historicalDto.getDate())
                .humidity(historicalDto.getHumidity())
                .pressure(historicalDto.getPressure())
                .temperature(historicalDto.getTemp())
                .build();
    }
}
