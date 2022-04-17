package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OWCurrentWeatherMainResponse implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6757609216721770500L;

	@JsonProperty("temp")
	private Double temperature;
	
	@JsonProperty("feels_like")
	private Double feelsLike;
	
	private BigDecimal pressure;
	
	private Double humidity;
	
	@JsonProperty("temp_min")
	private Double minimumTemperature;
	
	@JsonProperty("temp_max")
	private Double maximumTemperature;
	
	@JsonProperty("sea_level")
	private BigDecimal sealLevel;
	
	@JsonProperty("grnd_level")
	private BigDecimal groundLevel;
}
