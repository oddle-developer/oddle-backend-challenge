package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OWCurrentWeatherCloudResponse implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3736294093761168904L;
	private Double all;
	
}
