/**
 * Dana.id
 * Copyright (c) 2017‐2021 All Rights Reserved.
 */
package com.oddle.app.weather;

public enum WeatherErrorMap {

    SUCCESS("0000", "SUCCESS"),
    SYSTEM_FAILURE("00001", "SYSTEM FAILURE"),
    ILLEGAL_FORMAT("00002", "ILLEGAL FORMAT"),
    COMMUNICATION_FAILURE("00003", "COMMUNICATION FAILURE"),
    MISSING_FIELD("00004", "MISSING FIELD"),
    BAD_REQUEST("00005", "BAD REQUEST"),
    ;

    private String code;
    private String description;

    WeatherErrorMap(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
