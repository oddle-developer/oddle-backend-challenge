package com.oddle.app.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.common.Constant;
import com.oddle.app.weather.dto.HistoricalDto;
import com.oddle.app.weather.dto.HistoricalWeatherResponse;
import com.oddle.app.weather.dto.OpenWeatherResponse;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.mapper.WeatherMapper;
import com.oddle.app.weather.repository.WeatherRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    GeoCodeService geoCodeService;
    @Autowired
    WeatherMapper     mapper;
    @Autowired
    WeatherRepository weatherRepository;

    WebClient webClient = WebClient.create("https://api.openweathermap.org/data/2.5");


    @Override
    public WeatherResponse getCurrent(String city) {

        OpenWeatherResponse res = webClient.get()
                .uri("/weather?q="+ city +"&appid=ac0f3d4cc8ab37ae0e3a6c0eb9d8afc8")
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .block();

        assert res != null;
        return WeatherResponse.builder()
                .city(res.getName())
                .temperature(res.getMain().temp)
                .country(res.sys.country)
                .timeZone(res.timezone)
                .weather(res.weather)
                .build();
    }

    @Override
    public HistoricalDto getHistorical(String city, String date) throws JsonProcessingException {

        val geocode = geoCodeService.getLatLonByCity(city);

        HistoricalWeatherResponse res = webClient.get()
                .uri("/onecall/timemachine?lat="+geocode.getLat()+"&lon="+geocode.getLon()+"&dt="+date+"&appid="+ Constant.API_KEY)
                .retrieve()
                .bodyToMono(HistoricalWeatherResponse.class)
                .block();

        assert res != null;
        val dto  = HistoricalDto.builder()
                .city(city)
                .date(res.getCurrent().getDatetime())
                .temp(res.getCurrent().getTemp())
                .humidity(res.getCurrent().getHumidity())
                .pressure(res.getCurrent().getPressure())
                .weather(res.getCurrent().getWeather().get(0).description)
                .timezone(res.getTimezone())
                .build();

        val weatherEntity = mapper.weatherResponseToEntity(city, dto);
        return dto;
    }

    @Override
    public HistoricalDto saveHistorical(String city, String date) throws JsonProcessingException {

        val geocode = geoCodeService.getLatLonByCity(city);

        HistoricalWeatherResponse res = webClient.get()
                .uri("/onecall/timemachine?lat="+geocode.getLat()+"&lon="+geocode.getLon()+"&dt="+date+"&appid="+ Constant.API_KEY)
                .retrieve()
                .bodyToMono(HistoricalWeatherResponse.class)
                .block();

        assert res != null;
        val dto  = HistoricalDto.builder()
                .date(res.getCurrent().getDatetime())
                .temp(res.getCurrent().getTemp())
                .humidity(res.getCurrent().getHumidity())
                .pressure(res.getCurrent().getPressure())
                .weather(res.getCurrent().getWeather().get(0).description)
                .timezone(res.getTimezone())
                .build();

        val weatherEntity = mapper.weatherResponseToEntity(city, dto);

        weatherRepository.save(weatherEntity);
        return dto;
    }

    @Override
    public void deleteWeather(String city) {
        weatherRepository.deleteWeatherByCity(city);
    }

    @Override
    public Weather updateWeather(HistoricalDto historicalDto) {
        return weatherRepository.updateWeatherByCity(historicalDto.getCity(), historicalDto.getWeather());
    }
}
