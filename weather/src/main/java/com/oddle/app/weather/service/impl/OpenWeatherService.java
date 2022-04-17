package com.oddle.app.weather.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.oddle.app.weather.adapter.OpenWeatherGatewayAdapter;
import com.oddle.app.weather.dto.request.weather.save.EditWeatherDataRequest;
import com.oddle.app.weather.dto.request.weather.save.SaveWeatherDataRequest;
import com.oddle.app.weather.dto.response.HistoryWeatherResponse;
import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.dto.response.history.WeatherSunResponse;
import com.oddle.app.weather.exception.CurrentCityWeatherException;
import com.oddle.app.weather.exception.DateException;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.weatherdata.Weather;
import com.oddle.app.weather.repository.WeatherDataRepository;
import com.oddle.app.weather.repository.impl.WeatherDataRepositoryImpl;
import com.oddle.app.weather.service.CityWeatherService;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Service("OpenWeatherService")
@Slf4j
public class OpenWeatherService implements WeatherService,CityWeatherService {
	
	@Autowired
	private OpenWeatherGatewayAdapter openWeatherGatewayAdapter;
	
	@Autowired
	private WeatherDataRepository weatherDataRepository;
	
	@Autowired
	private WeatherDataRepositoryImpl weatherDataRepositoryImpl;

	@Override
	public WeatherResponse getCurrentWeather(String city) throws CurrentCityWeatherException {	
		String trimmedCity = city.trim();
		if(trimmedCity.isEmpty()) {
			throw new CurrentCityWeatherException("City must be supplied");
		}
		
		return openWeatherGatewayAdapter.getCurrentWeather(trimmedCity);
	}

	@Override
	@Transactional
	public Weather save(SaveWeatherDataRequest saveWeatherDataRequest) throws DateException {
		ModelMapper modelMapper = new ModelMapper();
		Weather weather = modelMapper.map(saveWeatherDataRequest, Weather.class);

		weather.setDateTimeCalculated(DateUtil.getLocalDateTimeUTC(saveWeatherDataRequest.getDateTimeCalculated()));
		weather.getSun().setSunrise(DateUtil.getLocalDateTimeUTC(saveWeatherDataRequest.getSun().getSunrise()));
		weather.getSun().setSunset(DateUtil.getLocalDateTimeUTC(saveWeatherDataRequest.getSun().getSunset()));
		weather.setActive(true);
		log.info(weather.toString());
		
		return weatherDataRepository.save(weather);
	}

	@Override
	@Transactional
	public Weather edit(EditWeatherDataRequest editWeatherDataRequest) throws DateException, WeatherException {

		Weather weather = findWeatherById(editWeatherDataRequest.getId());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(editWeatherDataRequest, weather);

		
		weather.setDateTimeCalculated(DateUtil.getLocalDateTimeUTC(editWeatherDataRequest.getDateTimeCalculated()));
		weather.getSun().setSunrise(DateUtil.getLocalDateTimeUTC(editWeatherDataRequest.getSun().getSunrise()));
		weather.getSun().setSunset(DateUtil.getLocalDateTimeUTC(editWeatherDataRequest.getSun().getSunset()));
	
		return weatherDataRepository.save(weather);
	}
	
	/**
	 * if id not found throw {@link WeatherException} 
	 * @param id
	 * @return
	 * @throws WeatherException
	 */
	private Weather findWeatherById(Long id) throws WeatherException {
		Weather weather = weatherDataRepository.findByIdAndIsActive(id,true)
		.orElseThrow(()->new WeatherException("id not found: "+id));
		
		return weather;
	}

	@Override
	@Transactional
	public Weather delete(Long id) throws WeatherException {
		Weather weather = findWeatherById(id);
		weather.setActive(false);
		return weatherDataRepository.save(weather);
	}

	@Override
	public Page<WeatherResponse> history(String cityName,LocalDate from, LocalDate to,int page, int size) {
		List<Weather> weathers = weatherDataRepositoryImpl.getWeatherHistory(cityName, 
				from.atStartOfDay(),
				to.plusDays(1).atStartOfDay(),
				page,
				size);
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Weather, HistoryWeatherResponse>() {
            @Override
            protected void configure() {
                skip(destination.getSun());
            }
        });
		return new PageImpl<WeatherResponse>(weathers.stream()
				.map(weather->{
					HistoryWeatherResponse history = modelMapper.map(weather, HistoryWeatherResponse.class);
					WeatherSunResponse sunData = new WeatherSunResponse();
					sunData.setSunriseDateTime(weather.getSun().getSunrise());
					sunData.setSunsetDateTime(weather.getSun().getSunset());
					history.setSun(sunData);
					return history;
				})
				.collect(Collectors.toList()));
	}

}
