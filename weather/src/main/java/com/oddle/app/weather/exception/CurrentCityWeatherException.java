package com.oddle.app.weather.exception;

public class CurrentCityWeatherException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092264624696569083L;

	public CurrentCityWeatherException() {
		super();
	}

	public CurrentCityWeatherException(String message) {
		super(message);
	}

	public CurrentCityWeatherException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrentCityWeatherException(Throwable cause) {
		super(cause);
	}
}
