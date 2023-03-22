package com.oddle.app.weather.model.update;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WeatherMainUpdate extends BaseModelUpdate{

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
