package com.oddle.app.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeatherDataNotFoundException extends RuntimeException{
    public WeatherDataNotFoundException(String message) {
        super("Weather Data " + message);
    }
}
