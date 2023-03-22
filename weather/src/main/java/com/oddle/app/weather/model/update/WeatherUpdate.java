package com.oddle.app.weather.model.update;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherUpdate extends BaseModelUpdate{

    private String base;

    private Integer visibility;

    private Long dateTimeCalculation;

    private Integer timezone;

    private String name;

    private String cod;

    private WeatherCoordinateUpdate weatherCoordinates;

    private WeatherMainUpdate weatherMain;

    private WeatherWindUpdate weatherWind;

    private WeatherCloudUpdate weatherCloud;

    @Schema(description = "Can be null")
    private WeatherRainUpdate weatherRain;

    @Schema(description = "Can be null")
    private WeatherSnowUpdate weatherSnow;

    private WeatherSunUpdate weatherSun;

    private List<WeatherCodeUpdate> codes = new ArrayList<>();
}
