package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private static final Double ABSOLUTE_TEMPERATURE_CONSTANT = 273.15;
    private static final Integer CONVERT_SECONDS_TO_HOURS = 3600;

    private DecimalFormat df;

    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    private double timeZone;
    @JsonProperty("temperature")
    private double temperature;
    @JsonProperty("weather")
    public ArrayList<Weather> weather;
    private String            weatherDesc;
    private double tempFeelsLike;
    private double tempMin;
    private double tempMax;
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("humidity")
    private double humidity;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        @JsonProperty("description")
        public String description;
        @JsonProperty("main")
        public String name;
    }
}
