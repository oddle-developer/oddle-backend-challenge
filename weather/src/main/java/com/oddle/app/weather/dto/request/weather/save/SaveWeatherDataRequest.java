package com.oddle.app.weather.dto.request.weather.save;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.oddle.app.weather.dto.request.WeatherDataRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SaveWeatherDataRequest extends WeatherDataRequest implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9089489766516227238L;
	
	@NotNull
	private String name;

}
