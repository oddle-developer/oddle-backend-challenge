package com.oddle.app.weather.dto.request;

import java.io.Serializable;

import com.oddle.app.weather.constant.WeatherCodeEnum;

import lombok.Data;

@Data
public class WeatherCodeRequest implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2599445760019278467L;

	private WeatherCodeEnum main;
	private String description;
	private String icon;
}
