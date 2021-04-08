package com.oddle.app.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.oddle.app.weather.model.Weather;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * This DTO class is used for Data Transfer and JSON Marshmallow
 */
@JsonPropertyOrder({
        WeatherResponse.JSON_ID,
        WeatherResponse.JSON_WEATHER,
        WeatherResponse.JSON_EXTEND,
        WeatherResponse.JSON_VISIBILITY,
        WeatherResponse.JSON_WIND_SPEED,
})
@Data
@EqualsAndHashCode(callSuper = true)
public class WeatherResponse extends Weather {

    public static final String JSON_ID = "id";
    public static final String JSON_WEATHER = "weather";
    public static final String JSON_WEATHER_CONDITION = "condition";
    public static final String JSON_WEATHER_DESCRIPTION = "description";
    public static final String JSON_EXTEND = "extend";
    public static final String JSON_EXTEND_TEMP_AVG = "temp_avg";
    public static final String JSON_EXTEND_TEMP_MIN = "temp_min";
    public static final String JSON_EXTEND_TEMP_MAX = "temp_max";
    public static final String JSON_EXTEND_HUMIDITY = "humidity";
    public static final String JSON_VISIBILITY = "visibility";
    public static final String JSON_WIND_SPEED = "wind_speed";

    @JsonProperty(value = JSON_ID)
    private String id;

    @JsonProperty(value = JSON_WEATHER)
    @JsonPropertyOrder({
            JSON_WEATHER_CONDITION,
            JSON_WEATHER_DESCRIPTION
    })
    private Map<String, Object> weather;

    @JsonProperty(value = JSON_EXTEND)
    @JsonPropertyOrder({
            JSON_EXTEND_TEMP_AVG,
            JSON_EXTEND_TEMP_MIN,
            JSON_EXTEND_TEMP_MAX,
            JSON_EXTEND_HUMIDITY
    })
    private Map<String, Object> extend;

    @JsonProperty(value = JSON_VISIBILITY)
    private int visibility;

    @JsonProperty(value = JSON_WIND_SPEED)
    private double windSpeed;
}
