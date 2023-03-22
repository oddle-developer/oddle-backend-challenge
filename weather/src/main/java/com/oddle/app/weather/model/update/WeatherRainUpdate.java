package com.oddle.app.weather.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherRainUpdate extends BaseModelUpdate{

    private Double oneHour;

    private Double threeHours;

    public WeatherRainUpdate(@JsonProperty("1h") Double oneHour, @JsonProperty("3h") Double threeHours) {
        this.oneHour = oneHour;
        this.threeHours = threeHours;
    }

}
