package com.oddle.app.weather.data.json.oddle.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class WeatherAbstractRequest extends WeatherDTO {

    @JsonProperty(value = WeatherResponse.JSON_WEATHER_CONDITION)
    private String condition;

    @JsonProperty(value = WeatherResponse.JSON_WEATHER_DESCRIPTION)
    private String description;

    @JsonProperty(value = WeatherResponse.JSON_EXTEND_TEMP_MIN)
    private double tempMin;

    @JsonProperty(value = WeatherResponse.JSON_EXTEND_TEMP_MAX)
    private double tempMax;

    @JsonProperty(value = WeatherResponse.JSON_EXTEND_HUMIDITY)
    private int humidity;

}
