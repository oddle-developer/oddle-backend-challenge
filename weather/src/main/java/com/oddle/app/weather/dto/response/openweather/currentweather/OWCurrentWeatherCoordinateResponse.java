package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.Coordinate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OWCurrentWeatherCoordinateResponse extends Coordinate implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5284687060888174769L;

	@Override
    @JsonProperty("lat")
	public Double getLatitude() {
		return super.getLatitude();
	}

	@Override
    @JsonProperty("lon")
	public Double getLongitude() {
		return super.getLongitude();
	}
	
}
