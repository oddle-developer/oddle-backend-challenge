package com.oddle.app.weather.api.exception;

public class ErrorApp extends RuntimeException {
    private String code;

    public ErrorApp() {
        super();
    }

    public ErrorApp(String code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorApp(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
