package com.oddle.app.weather.dto;

import com.oddle.app.weather.dto.client.model.Main;
import com.oddle.app.weather.dto.client.model.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherResponseDto {

    private String name;

    private Weather[] weather;

    private Main main;

}
