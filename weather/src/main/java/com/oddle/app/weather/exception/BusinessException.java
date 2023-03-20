package com.oddle.app.weather.exception;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 5627711206635791516L;

	private final String message;

	private final String errorType;

	private final transient Object[] args;

	public BusinessException(String message, @Nullable Object... args) {
		this.message = message;
		this.args = args;
		this.errorType = "ERROR";
	}
}
