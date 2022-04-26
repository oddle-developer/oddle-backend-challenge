package com.oddle.app.weather.dto.response.openweather.currentweather;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OWCurrentWeatherCodeResponse implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 277222630633237468L;
	private BigDecimal id;
	private String main;
	private String description;
	private String icon;
}
