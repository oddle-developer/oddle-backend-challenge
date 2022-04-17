package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.WindData;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class OWCurrentWeatherWindResponse extends WindData implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1072751505303217452L;

	@Override
	@JsonProperty("deg")
	public Double getDegree() {
		return super.getDegree();
	}
	
}
