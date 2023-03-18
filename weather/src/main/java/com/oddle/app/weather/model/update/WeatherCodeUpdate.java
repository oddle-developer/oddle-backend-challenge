package com.oddle.app.weather.model.update;

import com.oddle.app.weather.enumeration.WeatherCodeMainEnum;
import lombok.Data;

@Data
public class WeatherCodeUpdate {

    private Long id;

    private WeatherCodeMainEnum main;

    private String description;

    private String icon;
}
