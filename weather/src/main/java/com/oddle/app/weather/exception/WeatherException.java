package com.oddle.app.weather.exception;

import com.oddle.app.weather.WeatherErrorMap;

public class WeatherException extends Exception{

    private WeatherErrorMap errorMap;
    private String message;

    public WeatherException(WeatherErrorMap errorMap, String message) {
        this.errorMap = errorMap;
        this.message = message;
    }

    public WeatherErrorMap getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(WeatherErrorMap errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
