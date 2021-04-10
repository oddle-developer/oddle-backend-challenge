package com.oddle.app.weather.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.openweather.deserilizer.WeatherDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class WeatherModel {

    private String main;

    private String description;

}
