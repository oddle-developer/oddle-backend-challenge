package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oddle.app.weather.dto.WindData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherWindResponse extends WindData  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210153216615690770L;

}
