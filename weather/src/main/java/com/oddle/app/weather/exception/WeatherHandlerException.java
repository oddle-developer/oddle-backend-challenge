package com.oddle.app.weather.exception;

import com.oddle.app.base.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//handle global exception
@RestControllerAdvice
@Slf4j
public class WeatherHandlerException {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<ResponseDto> handleRestClientException(BusinessException businessException) {
		log.error("Weather Handler Exception - ", businessException);
		return ResponseEntity.badRequest().body(ResponseDto.fail(businessException.getMessage()));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResponseDto> handleException(Exception exception) {
		log.error("Weather Exception - ", exception);
		return ResponseEntity.badRequest().body(ResponseDto.fail(exception.getMessage()));
	}
}
