package com.oddle.app.weather.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {
    String message;
    T data;
    
    public void buildSuccessResponse(T data) {
    	this.message = "OK";
    	this.data = data;
    }
    
    public void buildFailResponse(String message) {
    	this.message = message;
    }
}
