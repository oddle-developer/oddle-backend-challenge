package com.oddle.app.weather.data.json.openweather.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.data.json.openweather.node.CoordNode;
import com.oddle.app.weather.data.json.openweather.node.MainNode;
import com.oddle.app.weather.data.json.openweather.node.WeatherNode;
import com.oddle.app.weather.data.json.openweather.node.WindNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPayload {

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("weather")
    private List<WeatherNode> weatherNode = new ArrayList<>();

    @JsonProperty("coord")
    private CoordNode coordNode;

    @JsonProperty("main")
    private MainNode mainNode;

    @JsonProperty("wind")
    private WindNode windNode;

}
