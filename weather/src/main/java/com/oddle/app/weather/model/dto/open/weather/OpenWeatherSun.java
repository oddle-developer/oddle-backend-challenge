package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherSun {

    private Integer type;

    private Long id;

    private String country;

    private Long sunrise;

    private Long sunset;
}
