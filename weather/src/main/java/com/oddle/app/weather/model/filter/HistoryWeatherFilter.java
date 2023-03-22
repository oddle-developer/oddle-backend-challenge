package com.oddle.app.weather.model.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HistoryWeatherFilter extends BaseFilter {

    private String cityName;

}
