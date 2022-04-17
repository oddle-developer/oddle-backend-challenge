package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.SunriseSunsetData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
@Data
public class OWCurrentWeatherSunriseSunsetResponse extends SunriseSunsetData implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6819331981147781300L;
	protected Long type;
	protected BigDecimal id;
	protected BigDecimal message;

}
