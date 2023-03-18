package com.oddle.app.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherDTO {

    private WeatherCoordinateDTO coord;

    private List<WeatherCodeDTO> weather = new ArrayList<>();

    private String base;

    private WeatherMainDTO main;

    private Integer visibility;

    private WeatherWindDTO wind;

    private WeatherRainDTO rain;

    private WeatherSnowDTO snow;

    private WeatherCloudDTO cloud;

    private Long dt;

    private WeatherSunDTO sys;

    private Integer timezone;

    private Long id;

    private String name;

    private Integer cod;

}
