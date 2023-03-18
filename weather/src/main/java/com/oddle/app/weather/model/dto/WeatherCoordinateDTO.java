package com.oddle.app.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherCoordinateDTO {

    private Double lon;

    private Double lat;
}
