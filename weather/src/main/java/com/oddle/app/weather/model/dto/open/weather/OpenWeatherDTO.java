package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherDTO {

    private OpenWeatherCoordinate coord;

    private List<OpenWeatherCode> weather;

    private String base;

    private OpenWeatherMain main;

    private Integer visibility;

    private OpenWeatherWind wind;

}
