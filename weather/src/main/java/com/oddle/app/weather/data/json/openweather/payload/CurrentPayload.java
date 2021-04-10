package com.oddle.app.weather.data.json.openweather.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.openweather.deserilizer.CoordDeserializer;
import com.oddle.app.weather.data.json.openweather.deserilizer.MainDeserializer;
import com.oddle.app.weather.data.json.openweather.deserilizer.WeatherDeserializer;
import com.oddle.app.weather.model.CoordModel;
import com.oddle.app.weather.model.MainModel;
import com.oddle.app.weather.model.WeatherModel;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPayload {

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("weather")
    @JsonDeserialize(using = WeatherDeserializer.class)
    private WeatherModel weatherNode;

    @JsonProperty("coord")
    @JsonDeserialize(using = CoordDeserializer.class)
    private CoordModel coordNode;

    @JsonProperty("main")
    @JsonDeserialize(using = MainDeserializer.class)
    private MainModel mainNode;

}
