package com.oddle.app.weather.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;


import lombok.Data;

@Data
public class WeatherDataMainRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387411746447802938L;

	private Double temperature;
	
	private Double feelsLike;
	
	private BigDecimal pressure;
	
	private Double humidity;
	
	private Double minimumTemperature;
	
	private Double maximumTemperature;
	
	private BigDecimal sealLevel;
	
	private BigDecimal groundLevel;
}
