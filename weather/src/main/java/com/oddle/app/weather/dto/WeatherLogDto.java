package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeatherLogDto implements Serializable {

	private static final long serialVersionUID = -1620885456354959774L;

	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;
	private String weatherType;
	private String weatherDesc;
	private String temporary;
	private String temporaryMin;
	private String temporaryMax;
	private String city;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
}
