package com.oddle.app.weather.exception;

public class WeatherException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3504934998226521430L;

	public WeatherException() {
		super();
	}

	public WeatherException(String message) {
		super(message);
	}

	public WeatherException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeatherException(Throwable cause) {
		super(cause);
	}
}
