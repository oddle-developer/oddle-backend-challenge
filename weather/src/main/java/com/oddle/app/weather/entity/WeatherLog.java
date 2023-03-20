package com.oddle.app.weather.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "weatherLog")
public class WeatherLog implements Serializable {

	private static final long serialVersionUID = 3930233224678098819L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "city")
	private String city;

	@Column(name = "weather_type")
	private String weatherType;

	@Column(name = "weather_description")
	private String weatherDesc;

	@Column(name = "temporary")
	private String temporary;

	@Column(name = "temporary_min")
	private String temporaryMin;

	@Column(name = "temporary_max")
	private String temporaryMax;

	@Column(name = "date")
	private String date;
}
