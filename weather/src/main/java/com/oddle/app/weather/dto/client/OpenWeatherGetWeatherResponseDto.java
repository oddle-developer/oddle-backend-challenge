package com.oddle.app.weather.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.client.model.Coord;
import com.oddle.app.weather.dto.client.model.Main;
import com.oddle.app.weather.dto.client.model.Sys;
import com.oddle.app.weather.dto.client.model.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherGetWeatherResponseDto {

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private Weather[] weather;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("name")
    private String name;


}
