package com.oddle.app.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class WeatherDTO {

    @JsonProperty(value = WeatherResponse.JSON_VISIBILITY)
    private int visibility;

    @JsonProperty(value = WeatherResponse.JSON_WIND_SPEED)
    private double windSpeed;

}
