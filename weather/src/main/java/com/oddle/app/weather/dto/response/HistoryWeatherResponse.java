package com.oddle.app.weather.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.oddle.app.weather.dto.response.history.WeatherCloudResponse;
import com.oddle.app.weather.dto.response.history.WeatherCodeResponse;
import com.oddle.app.weather.dto.response.history.WeatherCoordinateResponse;
import com.oddle.app.weather.dto.response.history.WeatherMainResponse;
import com.oddle.app.weather.dto.response.history.WeatherRainResponse;
import com.oddle.app.weather.dto.response.history.WeatherSnowResponse;
import com.oddle.app.weather.dto.response.history.WeatherSunResponse;
import com.oddle.app.weather.dto.response.history.WeatherWindResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class HistoryWeatherResponse extends WeatherResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4210795979897609021L;

	private String base;
	
	private LocalDateTime dateTimeCalculated;
	
	private Integer visibility;
	
	private WeatherMainResponse main;
	
	private WeatherCoordinateResponse coordinate;
	
	private List<WeatherCodeResponse> codes;
	
	private WeatherWindResponse wind;
	
	private WeatherCloudResponse cloud;
	
	private WeatherRainResponse rain;
	
	private WeatherSnowResponse snow;
	
	private WeatherSunResponse sun;
}
