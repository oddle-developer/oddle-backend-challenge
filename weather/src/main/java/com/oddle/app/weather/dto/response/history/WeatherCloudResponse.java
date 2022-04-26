package com.oddle.app.weather.dto.response.history;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherCloudResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6846475710906787407L;
	private Double all;
}
