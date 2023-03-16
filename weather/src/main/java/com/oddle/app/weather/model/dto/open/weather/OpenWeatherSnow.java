package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherSnow {

    private Double oneHour;

    private Double threeHours;

    public OpenWeatherSnow(@JsonProperty("1h") Double oneHour, @JsonProperty("3h") Double threeHours) {
        this.oneHour = oneHour;
        this.threeHours = threeHours;
    }
}
