package com.oddle.app.weather.model.weatherdata;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather", nullable = false))
public class Weather extends StandardAudibleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1500721550852602938L;

	@Column(length = 200)
	private String base = "stations";
	
	@Column(length = 200, nullable=false)
	private String name;
	
	@Column(columnDefinition = "integer default 0")
	private Long timezone;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateTimeCalculated;
	
	@Column(columnDefinition = "integer default 0")
	private Integer visibility;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_main")
	private WeatherMain main;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_coordinates")
	private WeatherCoordinates coordinate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="weather_join_weather_code",
			joinColumns=@JoinColumn(name="id_weather"),
			inverseJoinColumns=@JoinColumn(name="id_weather_code"))
	private List<WeatherCode> codes = new ArrayList<>();

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_wind")
	private WeatherWind wind;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_cloud")
	private WeatherCloud cloud;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_rain")
	private WeatherRain rain;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_snow")
	private WeatherSnow snow;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_weather_sun")
	private WeatherSun sun;
	
	private boolean isActive;
}
