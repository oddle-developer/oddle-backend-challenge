package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherMainResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2298297132248213443L;

	private Double temperature;
	
	private Double feelsLike;
	
	private BigDecimal pressure;
	
	private Double humidity;
	
	private Double minimumTemperature;
	
	private Double maximumTemperature;
	
	private BigDecimal sealLevel;
	
	private BigDecimal groundLevel;
}
