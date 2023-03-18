package com.oddle.app.weather.model.update;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class WeatherUpdate {

    private Long id;

    private String base;

    private Integer visibility;

    private Date dateTimeCalculation;

    private Integer timezone;

    private String name;

    private String cod;

    private WeatherCoordinateUpdate weatherCoordinates;

    private WeatherMainUpdate weatherMain;

    private WeatherWindUpdate weatherWind;

    private WeatherCloudUpdate weatherCloud;

    private WeatherRainUpdate weatherRain;

    private WeatherSnowUpdate weatherSnow;

    private WeatherSunUpdate weatherSun;

    private List<WeatherCodeUpdate> codes = new ArrayList<>();
}
