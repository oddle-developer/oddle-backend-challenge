package com.oddle.app.weather.exception.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.exception.FetchException;

public class OpenWeatherFetchException extends FetchException {

    public OpenWeatherFetchException(JsonProcessingException e) {

    }
}
