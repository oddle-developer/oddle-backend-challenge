package com.oddle.app.weather.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class WeatherDataCloudRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7581554435979597543L;
	
	private Double allPercentage;
}
