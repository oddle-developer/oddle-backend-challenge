package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.BaseFallableVolume;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherFallableVolume extends BaseFallableVolume implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1716464748821120314L;

	@JsonProperty("1h")
	@Override
	public Double getOneHour() {
		return super.getOneHour();
	}

	@JsonProperty("3h")
	@Override
	public Double getThreeHours() {
		return super.getThreeHours();
	}
}
