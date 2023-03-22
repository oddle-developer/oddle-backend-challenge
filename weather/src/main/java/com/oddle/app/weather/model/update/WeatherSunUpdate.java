package com.oddle.app.weather.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherSunUpdate extends BaseModelUpdate {

    private Integer type;

    private Long id;

    private String countryCode;

    private Long sunRise;

    private Long sunSet;
}
