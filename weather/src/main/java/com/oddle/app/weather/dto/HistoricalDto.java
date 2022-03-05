package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDto {

    private Instant date;
    private double temp;
    private String weather;
    private int humidity;
    private int pressure;
    private String timezone;
    private String city;

}
