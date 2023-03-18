package com.oddle.app.weather.model.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class WeatherWindUpdate {

    private Double speed;

    private Integer deg;

    private Double gust;
}
