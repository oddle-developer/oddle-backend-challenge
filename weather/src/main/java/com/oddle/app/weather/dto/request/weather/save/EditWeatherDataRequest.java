package com.oddle.app.weather.dto.request.weather.save;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.oddle.app.weather.dto.request.WeatherDataRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EditWeatherDataRequest extends WeatherDataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1661786236994954524L;

	@NotNull
	private String name;
	
	@NotNull
	private long id;
}
