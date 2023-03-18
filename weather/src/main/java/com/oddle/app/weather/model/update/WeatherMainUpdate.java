package com.oddle.app.weather.model.update;

import com.sun.istack.Nullable;
import lombok.Data;

@Data
public class WeatherMainUpdate {

    private Double temperature;

    private Double feelsLike;

    private Double temperatureMin;

    private Double temperatureMax;

    private Integer pressure;

    private Double humidity;

    @Nullable
    private Integer seaLevel;

    @Nullable
    private Integer groundLevel;
}
