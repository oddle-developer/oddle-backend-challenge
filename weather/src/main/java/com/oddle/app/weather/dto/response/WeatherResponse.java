package com.oddle.app.weather.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class WeatherResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6606549185330036335L;
	
	protected Long id;
	
	protected String name;

}
