package com.oddle.app.weather.model.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class WeatherSunUpdate {

    private Integer type;

    private Long id;

    private String countryCode;

    private Long sunRise;

    private Long sunSet;
}
