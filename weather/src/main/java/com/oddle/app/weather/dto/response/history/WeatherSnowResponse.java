package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.BaseFallableVolume;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherSnowResponse extends BaseFallableVolume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8115247571970122063L;

}
