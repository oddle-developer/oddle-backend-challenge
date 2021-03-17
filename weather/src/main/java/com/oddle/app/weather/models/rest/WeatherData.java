
package com.oddle.app.weather.models.rest;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.models.rest.weather.Clouds;
import com.oddle.app.weather.models.rest.weather.Coordinate;
import com.oddle.app.weather.models.rest.weather.Main;
import com.oddle.app.weather.models.rest.weather.System;
import com.oddle.app.weather.models.rest.weather.Weather;
import com.oddle.app.weather.models.rest.weather.Wind;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherData {

    @JsonProperty("history_id")
    private Long historyId;
    @JsonProperty("date_posted")
    private String datePosted;


    @JsonProperty("coord")
    private Coordinate coordinate;
    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("sys")
    private System system;
    @JsonProperty("timezone")
    private Integer timezone;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;
}
