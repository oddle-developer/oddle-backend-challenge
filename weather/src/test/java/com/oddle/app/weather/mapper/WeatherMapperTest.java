package com.oddle.app.weather.mapper;

import com.oddle.app.weather.data.WeatherResponse;
import com.oddle.app.weather.data.mapper.WeatherMapper;
import com.oddle.app.weather.model.Weather;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherMapperTest {

    /**
     * The Test Subject
     */
    private static WeatherMapper weatherMapper;

    /**
     * The Test Stub
     */
    private Weather weatherEntity;

    @BeforeAll
    public static void initSubject() {
        weatherMapper = WeatherMapper.INSTANCE;
    }

    @BeforeEach
    public void resetStub() {
        weatherEntity = new Weather();
    }

    @Test
    public void givenEntity_whenMap_returnResponseDTO() {
        weatherEntity.setId(UUID.randomUUID().toString());
        weatherEntity.setWeatherCondition("Cold");
        weatherEntity.setConditionDescription("Condition Description");
        weatherEntity.setTempAvg(new BigDecimal("0.2"));
        weatherEntity.setTempMin(new BigDecimal("0.2"));
        weatherEntity.setTempMax(new BigDecimal("0.3"));
        weatherEntity.setHumidity(1);
        weatherEntity.setVisibility(1);
        weatherEntity.setWindSpeed(0.3);

        WeatherResponse response = weatherMapper.mapEntityToResponse(weatherEntity);

        assertEquals(weatherEntity.getId(), response.getId());
        assertEquals(weatherEntity.getWeatherCondition(), String.valueOf(
                response.getWeather().get(WeatherResponse.JSON_WEATHER_CONDITION)));
        assertEquals(weatherEntity.getConditionDescription(), String.valueOf(
                response.getWeather().get(WeatherResponse.JSON_WEATHER_DESCRIPTION)));
        assertEquals(weatherEntity.getTempAvg(), response.getExtend()
                .get(WeatherResponse.JSON_EXTEND_TEMP_AVG));
        assertEquals(weatherEntity.getTempMin(), response.getExtend()
                .get(WeatherResponse.JSON_EXTEND_TEMP_MIN));
        assertEquals(weatherEntity.getHumidity(), (response.getExtend()
                .get(WeatherResponse.JSON_EXTEND_HUMIDITY)));
        assertEquals(weatherEntity.getVisibility(), response.getVisibility());
        assertEquals(weatherEntity.getWindSpeed(), response.getWindSpeed());
    }
}
