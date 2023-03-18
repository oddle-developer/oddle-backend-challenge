package com.oddle.app.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherByParamsRequestDto {

    String cityName;

    String cityId;

    String zipCode;

}
