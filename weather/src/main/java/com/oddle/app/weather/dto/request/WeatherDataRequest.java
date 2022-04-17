package com.oddle.app.weather.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WeatherDataRequest implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8119425002901394845L;

	private CoordinateRequest coordinate;
	
	private List<WeatherCodeRequest> codes;
	
	private WeatherDataMainRequest main;
	
	private Integer visibility;
	
	private WeatherDataWindRequest wind;
	
	private WeatherDataCloudRequest cloud;
	
	private WeatherDataRainRequest rain;
	
	private WeatherDataSnowRequest snow;
	
	private Long dateTimeCalculated;
	
	private WeatherDataSunriseSunsetDataRequest sun;
	
	private Long timezone;

}
