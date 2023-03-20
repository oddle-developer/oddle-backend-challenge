package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.config.RestClientException;
import com.oddle.app.weather.dto.WeatherLogDto;
import com.oddle.app.weather.dto.WeatherResponseDto;
import com.oddle.app.weather.entity.WeatherLog;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.WeatherService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public WeatherLogDto getWeatherByCityName(String city) {
		try{
			RestTemplate restTemplate = new RestTemplate();
			String uri =
				"https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=6017eefecc6b8fe6ed5dcfbe053b592f&units"
					+ "=metric";

			ResponseEntity<WeatherResponseDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
			                                                                          WeatherResponseDto.class);

			return this.mapper.map(responseEntity.getBody(), WeatherLogDto.class);
		}catch (Exception e){
			log.error(e.getMessage());
			if (e.getMessage().contains("city not found")){
				throw new RestClientException("City not found", e);
			}else{
				throw new RestClientException("Rest Error", e);
			}
		}
	}

	@Override
	public WeatherLogDto save(WeatherLogDto dto) {
		Optional<WeatherLog> optionalWeatherLog = repository.getByCityAndDate(dto.getCity(), dto.getDate());
		if (optionalWeatherLog.isPresent()){
			throw new RestClientException("Duplicate");
		}
		repository.save(this.mapper.map(dto, WeatherLog.class));
		return dto;
	}

	@Override
	public WeatherLogDto getPastPeriod(String city, String date) {
		Optional<WeatherLog> optionalWeatherLog = repository.getByCityAndDate(city, date);

		return this.mapper.map(optionalWeatherLog.get(), WeatherLogDto.class);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void updateWeather(WeatherLogDto dto) {
		repository.save(this.mapper.map(dto, WeatherLog.class));
	}
}
