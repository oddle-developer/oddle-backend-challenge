package com.oddle.app.weather.model.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherWindUpdate extends BaseModelUpdate{

    private Double speed;

    private Integer deg;

    private Double gust;
}
