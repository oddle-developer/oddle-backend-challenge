package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {

    @JsonProperty("name")
    public String name;

    @JsonProperty("main")
    public Main main;

    @JsonProperty("sys")
    public Sys sys;

    public String base;
    public Clouds clouds;
    public int cod;
    public Coord coord;
    public int dt;
    public int id;
    @JsonProperty("timezone")
    public int timezone;
    public int                                visibility;
    @JsonProperty("weather")
    public ArrayList<WeatherResponse.Weather> weather;
    public Wind                               wind;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds{
        @JsonProperty("all")
        public int all;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord{
        @JsonProperty("lat")
        public double lat;
        @JsonProperty("lon")
        public double lon;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main implements Serializable {
        @JsonProperty("temp")
        public double temp;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys{
        public String country;
        public int id;
        public int sunrise;
        public int sunset;
        public int type;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather{
        @JsonProperty("description")
        public String description;
        public String icon;
        public int id;
        @JsonProperty("main")
        public String main;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind{
        public int deg;
        public double speed;
    }

    public OpenWeatherResponse(Sys sys) {
        this.sys = sys;
    }

    public OpenWeatherResponse(Main main) {
        this.main = main;
    }
}
