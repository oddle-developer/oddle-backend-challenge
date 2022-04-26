package com.oddle.app.weather.model.weatherdata;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather_wind")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_wind", nullable = false))
public class WeatherWind extends StandardAudibleEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222560662150577368L;

	@Column(columnDefinition="Decimal(10,2) default '0'")
	protected Double speed;

	@Column(columnDefinition="Decimal(10,2) default '0'")
	protected Double degree;
	
	@Column(columnDefinition="Decimal(10,2) default '0'")
	protected Double gust;
}
