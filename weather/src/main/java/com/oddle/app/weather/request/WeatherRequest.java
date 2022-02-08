package com.oddle.app.weather.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {
    @NonNull
    private String city;
    @NonNull
    private String weather;
    @NonNull
    private String description;
    @NonNull
    private String icon;
}
