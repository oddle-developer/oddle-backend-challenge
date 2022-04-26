package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.Coordinate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherCoordinateResponse extends Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5089645955649211767L;

}
