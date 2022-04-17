package com.oddle.app.weather.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class WindData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4880496853681244182L;
	
	protected Double speed;

	protected Double degree;
	
	protected Double gust;

}
