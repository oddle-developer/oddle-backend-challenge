package com.oddle.app.weather.dto.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coord {

    @JsonProperty("lon")
    private float lon;

    @JsonProperty("lat")
    private float lat;

}
