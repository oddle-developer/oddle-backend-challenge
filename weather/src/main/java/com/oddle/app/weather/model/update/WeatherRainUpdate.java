package com.oddle.app.weather.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherRainUpdate {

    private Double oneHour;

    private Double threeHours;


    public WeatherRainUpdate(@JsonProperty("1h") Double oneHour, @JsonProperty("3h") Double threeHours) {
        this.oneHour = oneHour;
        this.threeHours = threeHours;
    }

}
