package com.oddle.app.weather.model.weatherdata;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oddle.app.weather.constant.WeatherCodeEnum;
import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather_code")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_code", nullable = false))
public class WeatherCode extends StandardAudibleEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5097821791889275572L;
	
	private WeatherCodeEnum main;
	
	@Column(length = 255)
	private String description;
	
	@Column(length = 50)
	private String icon;
}
