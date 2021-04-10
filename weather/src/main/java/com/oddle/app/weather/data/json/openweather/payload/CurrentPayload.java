package com.oddle.app.weather.data.json.openweather.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.openweather.deserilizer.CoordDeserializer;
import com.oddle.app.weather.data.json.openweather.deserilizer.MainDeserializer;
import com.oddle.app.weather.data.json.openweather.deserilizer.WeatherDeserializer;
import com.oddle.app.weather.data.json.openweather.deserilizer.WindDeserializer;
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
    @JsonDeserialize(using = WeatherDeserializer.class)
    private List<WeatherNode> weatherNode = new ArrayList<>();

    @JsonProperty("coord")
    @JsonDeserialize(using = CoordDeserializer.class)
    private CoordNode coordNode;

    @JsonProperty("main")
    @JsonDeserialize(using = MainDeserializer.class)
    private MainNode mainNode;

    @JsonProperty("wind")
    @JsonDeserialize(using = WindDeserializer.class)
    private WindNode windNode;

}
