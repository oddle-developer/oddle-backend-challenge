package com.oddle.app.weather.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalWeatherResponse {
    private Current current;
    @JsonProperty("timezone")
    private String timezone;

    @Getter @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        @JsonProperty("dt")
        private Instant datetime;
        @JsonProperty("temp")
        private double  temp;
        @JsonProperty("weather")
        private ArrayList<OpenWeatherResponse.Weather> weather;
        @JsonProperty("humidity")
        private int humidity;
        @JsonProperty("pressure")
        private int pressure;
    }
}
