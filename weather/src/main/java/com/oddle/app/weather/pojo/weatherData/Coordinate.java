package com.oddle.app.weather.pojo.weatherData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coordinate {
    private double longitude;
    private double latitude;

    @JsonProperty("longitude")
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty("lon")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public double getLatitude() {
        return latitude;
    }

    @JsonProperty("lat")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
