package com.oddle.app.weather.dto.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main {

    @JsonProperty("temp")
    private float temp;

    @JsonProperty("feels_like")
    private float feels_like;

    @JsonProperty("temp_min")
    private float temp_min;

    @JsonProperty("temp_max")
    private float temp_max;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

}
