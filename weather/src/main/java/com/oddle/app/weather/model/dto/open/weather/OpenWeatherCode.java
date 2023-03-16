package com.oddle.app.weather.model.dto.open.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherCode {

    private Long id;

    private String main;

    private String description;

    private String icon;
}
