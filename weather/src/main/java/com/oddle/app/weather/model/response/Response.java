package com.oddle.app.weather.model.response;

import org.springframework.http.HttpStatus;

public class Response<T> {

    private int statusCode;

    private T data;

    private String message;

    private Response(int statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(HttpStatus.OK.value(), data , HttpStatus.OK.getReasonPhrase());
    }

    public static <T> Response<T> failed(String message){
        return new Response<T>(HttpStatus.BAD_REQUEST.value(), null , HttpStatus.OK.getReasonPhrase());
    }
}
