package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherCoordinate {

    private Double lon;

    private Double lat;
}
