package com.oddle.app.weather.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SunriseSunsetData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1851779739231149609L;
	protected String country;
	protected Long sunrise;
	protected Long sunset;
}
