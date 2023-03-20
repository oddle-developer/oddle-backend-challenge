package com.oddle.app.weather.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "weatherLog")
@EntityListeners(AuditingEntityListener.class)
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
	@Temporal(value = TemporalType.DATE)
	private Date date;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = true)
	private Date createdDate;

	@CreatedBy
	@Column(name = "created_by", nullable = true)
	private String createdBy;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = true)
	private Date updatedDate;

	@LastModifiedBy
	@Column(name = "updated_by", nullable = true)
	private String updatedBy;
}
