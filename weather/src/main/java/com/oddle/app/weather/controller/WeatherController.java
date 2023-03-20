package com.oddle.app.weather.controller;

import com.oddle.app.base.dto.ResponseDto;
import com.oddle.app.weather.dto.WeatherLogDto;
import com.oddle.app.weather.service.WeatherService;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("")
	public ResponseDto<WeatherLogDto> getWeather(@RequestParam("city") String cityName) {
		return ResponseDto.ok(weatherService.getWeatherByCityName(cityName));
	}

	@PostMapping("/save")
	public ResponseDto<WeatherLogDto> save(@RequestBody WeatherLogDto weather) {
		weatherService.save(weather);
		return ResponseDto.ok(null);
	}

	@DeleteMapping("/{id}")
	public ResponseDto<WeatherLogDto> delete(@PathVariable Long id) {
		weatherService.deleteById(id);
		return ResponseDto.ok(null);
	}

	@GetMapping("/past")
	public ResponseDto<List<WeatherLogDto>> getPastPeriod(@RequestParam("city") String cityName,
	                                                      @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
	                                                      @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {
		return ResponseDto.ok(weatherService.getPastPeriod(cityName, startDate, endDate));
	}

	@PutMapping
	public ResponseDto<WeatherLogDto> update(@RequestBody WeatherLogDto dto) {
		weatherService.updateWeather(dto);
		return ResponseDto.ok(null);
	}
}