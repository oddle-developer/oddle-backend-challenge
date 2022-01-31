package com.oddle.app.weather.pojo.weatherData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Clouds {
    private short cloudiness;

    @JsonProperty("cloudiness")
    public short getCloudiness() {
        return cloudiness;
    }

    @JsonProperty("all")
    public void setCloudiness(short cloudiness) {
        this.cloudiness = cloudiness;
    }
}
