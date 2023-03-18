package com.oddle.app.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherSunDTO {

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private Long sunRise;

    @JsonProperty("sunset")
    private Long sunSet;
}
