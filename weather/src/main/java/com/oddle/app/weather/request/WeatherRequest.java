package com.oddle.app.weather.request;

import lombok.Data;
import lombok.NonNull;

@Data
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
