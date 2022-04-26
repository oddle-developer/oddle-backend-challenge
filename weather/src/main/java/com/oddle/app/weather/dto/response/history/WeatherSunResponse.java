package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.SunriseSunsetData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=true)
public class WeatherSunResponse extends SunriseSunsetData implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1663917004659953012L;

	
	private LocalDateTime sunriseDateTime;
	private LocalDateTime sunsetDateTime;
	
}
