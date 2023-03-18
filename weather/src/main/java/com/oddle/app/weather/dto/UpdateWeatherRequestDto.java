package com.oddle.app.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWeatherRequestDto {

    private String cityName;

    private String weather;

    private int temperature;

    private int humidity;

}
