package com.oddle.app.weather.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private Long id;
    private String city;
    private String weather;
    private String description;
    private String icon;
}
