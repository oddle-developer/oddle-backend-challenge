package com.oddle.app.weather.response;

public enum Status {
    SUCCESS("A00"),
    NO_HISTORICAL_DATA("A01"),
    CITY_NOT_FOUND("E01"),
    FAILED_TO_SAVE_DATA("E02"),
    FAILED_TO_UPDATE_DATA("E03"),
    DATA_ALREADY_EXISTS("E04"),
    DATA_NOT_FOUND("E05"),
    FAILED_TO_DELETE_DATA("E06"),
    UNDEFINED_ERROR("E99");

    private String responseCode;

    Status(String responseCode){
        this.responseCode = responseCode;
    }

    public String responseCode(){
        return responseCode;
    }
}
