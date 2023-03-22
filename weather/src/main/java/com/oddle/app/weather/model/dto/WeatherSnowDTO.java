package com.oddle.app.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherSnowDTO {

    @JsonProperty("1h")
    private Double oneHour;

    @JsonProperty("3h")
    private Double threeHours;

}
