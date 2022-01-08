package com.oddle.app.weather.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class BaseResponse {
    String message = "OK";
}
