package com.oddle.app.weather.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherCoordinateUpdate extends BaseModelUpdate{

    private Double latitude;

    private Double longitude;
}
