package com.oddle.app.weather.data.transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequest extends WeatherAbstractRequest {

    private String id;

}
