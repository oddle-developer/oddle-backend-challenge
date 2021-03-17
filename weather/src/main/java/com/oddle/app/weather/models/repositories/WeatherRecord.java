package com.oddle.app.weather.models.repositories;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "weather_record")
@SuperBuilder
@Data
@DynamicUpdate
@EnableAutoConfiguration
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WeatherRecord implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_posted", nullable = false)
  private Date datePosted;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_updated", nullable = false)
  private Date lastUpdated;

  @Column(name = "city_id", nullable = false)
  private Integer cityId;

  @Column(name = "city_name", nullable = false)
  private String cityName;

  @Column(name = "timezone", nullable = false)
  private Integer timezone;

  @Column(name = "dt")
  private Integer dt;

  @Column(name = "visibility")
  private Integer visibility;

  @Column(name = "base")
  private String base;

  @Column(name = "coor_longitude")
  private Double coordinateLongitude;

  @Column(name = "coor_latitude")
  private Double coordinateLatitude;

  @OneToMany(mappedBy = "weatherRecord")
  @NotFound(action= NotFoundAction.IGNORE)
  List<Weather> weathers;

  @Column(name = "main_temperature")
  private Double mainTemperature;

  @Column(name = "main_feels_like")
  private Double mainFeelsLike;

  @Column(name = "main_temp_min")
  private Double mainTempMin;

  @Column(name = "main_temp_max")
  private Double mainTempMax;

  @Column(name = "main_pressure")
  private Integer mainPressure;

  @Column(name = "main_humidity")
  private Integer mainHumidity;

  @Column(name = "wind_speed")
  private Double windSpeed;

  @Column(name = "wind_degree")
  private Integer windDegree;

  @Column(name = "clouds_all")
  private Integer cloudsAll;

  @Column(name = "system_type")
  private Integer systemType;

  @Column(name = "system_id")
  private Integer systemId;

  @Column(name = "system_country")
  private String systemCountry;

  @Column(name = "system_sunrise")
  private Integer systemSunrise;

  @Column(name = "system_sunset")
  private Integer systemSunset;
}
