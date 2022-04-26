package com.oddle.app.weather.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Coordinate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142261376805185894L;
	
	protected Double longitude;

	protected Double latitude;

}
