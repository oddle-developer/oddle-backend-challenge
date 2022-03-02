package com.oddle.app.weather.dto;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResponseDTO implements Serializable {
    private Boolean status;
    private String message;
    private Serializable data;
    private int code;

    public ResponseDTO() {}

    private ResponseDTO(Boolean status, int code, Serializable data, String message) {
        this.status = status;
        this.code = code;
        this.data   = data;
        this.message  = message;
    }

    public static ResponseDTO SUCCESS(Serializable data) {
        return new ResponseDTO(true, 200, data, null);
    }

    public static ResponseDTO SUCCESS() {
        return new ResponseDTO(true, 200, null, null);
    }

    public static ResponseDTO ERROR(int code, String message) {
        return new ResponseDTO(false, code, null, message);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
