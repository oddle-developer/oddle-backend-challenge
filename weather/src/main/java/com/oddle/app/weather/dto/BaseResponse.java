package com.oddle.app.weather.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class BaseResponse {
    String message = "OK";

	public BaseResponse(String message) {
		super();
		this.message = message;
	}
    
    
}
