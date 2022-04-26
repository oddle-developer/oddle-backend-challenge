package com.oddle.app.weather.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * use this class for this api response object
 * @author johryanoliveros
 *
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private String status;
    private int statusCode;
    private T data;
    private String message;

    private Response(String status, int statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return success(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> Response<T> success(HttpStatus httpStatus, String message) {
        return success(httpStatus, message, null);
    }

    public static <T> Response<T> success(HttpStatus httpStatus, String message, T data) {
        return new Response<>(httpStatus.getReasonPhrase(), httpStatus.value(), message, data);
    }

    public static <T> Response<T> failed(String message) {
        return failed(HttpStatus.BAD_REQUEST, message);
    }

    public static <T> Response<T> failed(HttpStatus httpStatus, String message) {
        return new Response<>(httpStatus.getReasonPhrase(), httpStatus.value(), message, null);
    }
}
