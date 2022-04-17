package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.BaseFallableVolume;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherRainResponse extends BaseFallableVolume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627985397849954166L;

}
