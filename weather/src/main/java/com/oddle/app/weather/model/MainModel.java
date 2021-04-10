package com.oddle.app.weather.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.openweather.deserilizer.MainDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MainModel {

    private double temp;

    private double tempMin;

    private double tempMax;

    private int pressure;

    private int humidity;

}
