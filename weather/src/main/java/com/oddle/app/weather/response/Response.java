package com.oddle.app.weather.response;

public class Response {
    private String responseCode;
    private String message;
    private String descripton;

    public Response(String responseCode, String message, String descripton) {
        this.responseCode = responseCode;
        this.message = message;
        this.descripton = descripton;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}
