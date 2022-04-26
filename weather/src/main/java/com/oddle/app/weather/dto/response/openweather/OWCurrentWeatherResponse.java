package com.oddle.app.weather.dto.response.openweather;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherCloudResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherCodeResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherCoordinateResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherMainResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherRainResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherSnowResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherSunriseSunsetResponse;
import com.oddle.app.weather.dto.response.openweather.currentweather.OWCurrentWeatherWindResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class OWCurrentWeatherResponse extends WeatherResponse implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7172646399943617294L;

	@JsonProperty("coord")
	private OWCurrentWeatherCoordinateResponse coordinate;
	
	private List<OWCurrentWeatherCodeResponse> weather;
	
	private String base;
	
	private OWCurrentWeatherMainResponse main;
	
	private Integer visibility;
	
	private OWCurrentWeatherWindResponse wind;
	
	private OWCurrentWeatherCloudResponse clouds;
	
	private OWCurrentWeatherRainResponse rain;
	
	private OWCurrentWeatherSnowResponse snow;
	
	@JsonProperty("dt")
	private Long dateTimeGenerated;
	
	@JsonProperty("sys")
	private OWCurrentWeatherSunriseSunsetResponse sunriseSunset;
	
	private Long timezone;
	
	private Integer cod;
}
