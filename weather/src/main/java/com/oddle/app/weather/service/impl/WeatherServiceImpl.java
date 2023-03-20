package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.config.OpenWeatherMapConfigProperties;
import com.oddle.app.weather.exception.BusinessException;
import com.oddle.app.weather.dto.WeatherLogDto;
import com.oddle.app.weather.dto.WeatherResponseDto;
import com.oddle.app.weather.entity.WeatherLog;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.WeatherService;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository repository;

	private final ModelMapper mapper;

	private final OpenWeatherMapConfigProperties openWeatherMapConfigProperties;

	public WeatherServiceImpl(WeatherRepository repository, ModelMapper mapper,
	                          OpenWeatherMapConfigProperties openWeatherMapConfigProperties) {
		this.repository = repository;
		this.mapper = mapper;
		this.openWeatherMapConfigProperties = openWeatherMapConfigProperties;
	}

	@Override
	public WeatherLogDto getWeatherByCityName(String city) {
		log.info("get weather by city name - {}", city);
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfigProperties.getUrl())
			                              .queryParam("q", city)
			                              .queryParam("appid", openWeatherMapConfigProperties.getApiKey())
			                              .queryParam("units", "metric").build().toUri();

			ResponseEntity<WeatherResponseDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
			                                                                          WeatherResponseDto.class);

			return this.mapper.map(responseEntity.getBody(), WeatherLogDto.class);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			if (e.getMessage().contains("city not found")) {
				throw new BusinessException("City not found", e);
			}
			else {
				throw new BusinessException("Rest Error", e);
			}
		}
	}

	@Override
	public WeatherLogDto save(WeatherLogDto dto) {
		log.info("save function - {}", dto);
		if (repository.existsByCityAndDate(dto.getCity(), dto.getDate())) {
			throw new BusinessException("Duplicate");
		}
		repository.save(this.mapper.map(dto, WeatherLog.class));
		return dto;
	}

	@Override
	public List<WeatherLogDto> getPastPeriod(String city, Date startDate, Date endDate) {
		log.info("get past period - {} {} {}", city, startDate, endDate);
		if (Objects.isNull(endDate)) {
			endDate = startDate;
		}
		return repository.getByCityAndDateBetween(city, startDate, endDate)
		                 .stream()
		                 .map(e -> this.mapper.map(e, WeatherLogDto.class))
		                 .collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		log.info("delete by id - {}", id);
		repository.deleteById(id);
	}

	@Override
	public void updateWeather(WeatherLogDto dto) {
		log.info("update weather {}", dto);
		WeatherLog weatherLog = repository.findById(dto.getId()).orElseThrow(() -> new BusinessException("Not exist!"));
		this.mapper.map(dto, weatherLog);
		repository.save(weatherLog);
	}
}
