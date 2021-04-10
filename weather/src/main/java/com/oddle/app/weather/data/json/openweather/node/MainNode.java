package com.oddle.app.weather.data.json.openweather.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MainNode {

    private double temp;

    private double tempMin;

    private double tempMax;

    private int pressure;

    private int humidity;

}
