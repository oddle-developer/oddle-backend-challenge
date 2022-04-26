package com.oddle.app.weather.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.oddle.app.weather.adapter.OpenWeatherGatewayAdapter;
import com.oddle.app.weather.dto.request.WeatherDataSunriseSunsetDataRequest;
import com.oddle.app.weather.dto.request.weather.save.EditWeatherDataRequest;
import com.oddle.app.weather.dto.request.weather.save.SaveWeatherDataRequest;
import com.oddle.app.weather.dto.response.openweather.OWCurrentWeatherResponse;
import com.oddle.app.weather.exception.CurrentCityWeatherException;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.weatherdata.Weather;
import com.oddle.app.weather.model.weatherdata.WeatherSun;
import com.oddle.app.weather.repository.WeatherDataRepository;
import com.oddle.app.weather.repository.impl.WeatherDataRepositoryImpl;
import com.oddle.app.weather.service.impl.OpenWeatherService;


@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

	@InjectMocks
	OpenWeatherService openWeatherService;
	
	@Mock
	private OpenWeatherGatewayAdapter openWeatherGatewayAdapter;
	
	@Mock
	private WeatherDataRepository weatherDataRepository;
	
	@Mock
	private WeatherDataRepositoryImpl weatherDataRepositoryImpl;
	
	private Weather buildWeather() {
    	Weather weather = new Weather();
    	weather.setName("manila");
    	weather.setId(1l);
    	WeatherSun sun = new WeatherSun();
    	sun.setSunrise(LocalDateTime.now());
    	sun.setSunset(LocalDateTime.now());
    	weather.setSun(sun);
    	weather.setDateTimeCalculated(LocalDateTime.now());
    	return weather;
	}
	
	private EditWeatherDataRequest buildEditWeatherDataRequest() {
    	EditWeatherDataRequest request = new EditWeatherDataRequest();
    	request.setName("manila");
    	request.setId(1l);
    	request.setDateTimeCalculated(1650181108l);
    	
    	
    	WeatherDataSunriseSunsetDataRequest sunRequest = new WeatherDataSunriseSunsetDataRequest();
    	sunRequest.setSunrise(1650181108l);
    	sunRequest.setSunset(1650181108l);
    	request.setSun(sunRequest);
    	
    	return request;
	}
	
	private SaveWeatherDataRequest buildSaveWeatherDataRequest() {
		SaveWeatherDataRequest request = new SaveWeatherDataRequest();
    	request.setName("manila");
    	request.setDateTimeCalculated(1650181108l);
    	
    	
    	WeatherDataSunriseSunsetDataRequest sunRequest = new WeatherDataSunriseSunsetDataRequest();
    	sunRequest.setSunrise(1650181108l);
    	sunRequest.setSunset(1650181108l);
    	request.setSun(sunRequest);
    	
    	return request;
	}
	
	private OWCurrentWeatherResponse buildOWCurrentWeatherResponse() {
		OWCurrentWeatherResponse resp = new OWCurrentWeatherResponse();
		resp.setId(1l);
		resp.setName("manila");
		return resp;
	}
	
    @Test
    public void testDelete() {
    	Weather weather = buildWeather();
    	when(weatherDataRepository.findByIdAndIsActive(1l, true)).thenReturn(Optional.of(weather));
    	Assertions.assertDoesNotThrow(()->openWeatherService.delete(1l));
    }
    
    @Test
    public void testDeleteNotFound() {
    	Assertions.assertThrows(WeatherException.class,()->openWeatherService.delete(2l));
    }
	
    @Test
    public void testEdit() {
    	Weather weather = buildWeather();
    	when(weatherDataRepository.findByIdAndIsActive(1l, true)).thenReturn(Optional.of(weather));
    	Assertions.assertDoesNotThrow(()->openWeatherService.edit(buildEditWeatherDataRequest()));
    }
    
    @Test
    public void testEditNotFound() {
    	Assertions.assertThrows(WeatherException.class,()->openWeatherService.edit(buildEditWeatherDataRequest()));
    }
    
    @Test
    public void save() {
    	Assertions.assertDoesNotThrow(()->openWeatherService.save(buildSaveWeatherDataRequest()));
    }
    
    @Test
    public void saveValidation() {
    	Assertions.assertThrows(Exception.class,()->openWeatherService.save(new SaveWeatherDataRequest()));
    }
    
    @Test
    public void getCurrentWeatherTest() throws CurrentCityWeatherException {
    	when(openWeatherGatewayAdapter.getCurrentWeather("manila")).thenReturn(buildOWCurrentWeatherResponse());
    	Assertions.assertEquals(openWeatherService.getCurrentWeather("manila").getName(), "manila");
    }
    
    @Test
    public void getHistoryTest() throws CurrentCityWeatherException {
    	LocalDate from = LocalDate.now();
    	LocalDate to = LocalDate.now();
    	List<Weather> weathers = new ArrayList<>();
    	weathers.add(buildWeather());
    	
    	when(weatherDataRepositoryImpl.getWeatherHistory("manila", from.atStartOfDay(), to.plusDays(1).atStartOfDay(), 0, 10)).thenReturn(weathers);
    	Assertions.assertEquals(openWeatherService.history("manila", from, to, 0, 10).getSize(), 1);
    }
}
