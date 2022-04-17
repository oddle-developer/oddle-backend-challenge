package com.oddle.app.weather.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.oddle.app.weather.dto.response.Response;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@ControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class ExceptionHandling {

	
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	@ExceptionHandler(CurrentCityWeatherException.class)
	public Response handleCurrentCityWeatherException(CurrentCityWeatherException e) {
		log.info(e.getMessage());
		return Response.failed(HttpStatus.EXPECTATION_FAILED,e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(HttpClientErrorException.class)
	public Response handleHttpClientErrorException(HttpClientErrorException e) {
		log.info(e.getMessage());
		return Response.failed(HttpStatus.NOT_FOUND,e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	@ExceptionHandler(DateException.class)
	public Response handleDateExceptionException(DateException e) {
		log.info(e.getMessage());
		return Response.failed(HttpStatus.EXPECTATION_FAILED,e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	@ExceptionHandler(WeatherException.class)
	public Response handleException(WeatherException e) {
		log.info(e.getMessage());
		return Response.failed(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		log.info(e.getMessage());
		return Response.failed(e.getMessage());
	}
}
