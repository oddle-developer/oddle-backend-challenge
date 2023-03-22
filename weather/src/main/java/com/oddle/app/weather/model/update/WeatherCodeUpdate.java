package com.oddle.app.weather.model.update;

import com.oddle.app.weather.enumeration.WeatherCodeMainEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherCodeUpdate extends BaseModelUpdate {


    private WeatherCodeMainEnum main;

    private String description;

    private String icon;
}
