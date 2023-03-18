package com.oddle.app.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveWeatherDataResponseDto {

    private long id;

    private String cityName;

    private String weather;

    private int temperature;

    private int humidity;
}
