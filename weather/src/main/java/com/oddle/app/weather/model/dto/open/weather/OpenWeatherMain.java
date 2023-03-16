package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherMain {

    private Double temp;

    private Double feels_like;

    private Double temp_min;

    private Double temp_max;

    private Integer pressure;

    private Double humidity;

    private Integer sea_level;

    private Integer grnd_level ;
}
