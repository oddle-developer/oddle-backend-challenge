package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherCodeResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8151040814520965314L;
	private String main;
	private String description;
	private String icon;
}
