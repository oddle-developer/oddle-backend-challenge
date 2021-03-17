package com.oddle.app.weather.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.feign.client.OpenWeatherClient;
import com.oddle.app.weather.mapper.WeatherDataMapper;
import com.oddle.app.weather.models.repositories.WeatherRecord;
import com.oddle.app.weather.models.rest.Response;
import com.oddle.app.weather.models.rest.WeatherData;
import com.oddle.app.weather.models.rest.WeatherErrorData;
import com.oddle.app.weather.repositories.WeatherRecordRepository;
import com.oddle.app.weather.repositories.WeatherRepository;
import com.oddle.app.weather.service.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class WeatherServiceImpl implements WeatherService {

  @Autowired
  private OpenWeatherClient openWeatherClient;

  @Value("${open.weather.api.key}")
  private String apiKey;

  @Autowired
  private WeatherDataMapper weatherDataMapper;

  @Autowired
  private WeatherRecordRepository weatherRecordRepository;

  @Autowired
  private WeatherRepository weatherRepository;

  @Override
  public Response getWeatherByCity(String city) {
    WeatherData response = null;
    try {
       response = openWeatherClient.getWeatherByCity(city, apiKey);
    } catch (HttpClientErrorException e){
      log.error(e.getResponseBodyAsString());
      try {
        return Response.builder().code("404")
          .message("Error occurred.")
          .data(new ObjectMapper().readValue(e.getResponseBodyAsString(), WeatherErrorData.class)).build();
      } catch (JsonProcessingException je) {
        je.printStackTrace();
      }
    }

    saveWeatherData(response);

    return Response.builder().code("100")
      .message("Successfully retrieved.")
      .data(response).build();
  }

  @Override
  public boolean saveWeatherData(WeatherData weatherData) {

     WeatherRecord savedWeatherRecord = weatherRecordRepository.save(weatherDataMapper.createWeatherRecord(weatherData));

     weatherDataMapper.createWeathers(weatherData.getWeather()).stream().forEach(entry -> {

       entry.setWeatherRecord(savedWeatherRecord);

       weatherRepository.save(entry);
     });

    return true;
  }

  @Override
  public List<WeatherData> getWeatherDatasByCity(String city) {

    List<WeatherRecord> weatherHistories = weatherRecordRepository.findAllByCityName(city,
                                      Sort.by(Sort.Direction.DESC, "datePosted"));

    return weatherDataMapper.getWeatherDatas(weatherHistories);
  }

  @Override
  public boolean removeWeatherData(String weatherRecordId) {
   try {
     weatherRecordRepository.deleteById(Long.parseLong(weatherRecordId));
   }catch (Exception e){
     return false;
   }
    return true;
  }

  @Override
  public WeatherRecord updateWeatherData(String weatherId, WeatherData weatherData) {

    Optional<WeatherRecord> weatherRecord = weatherRecordRepository.findById(Long.parseLong(weatherId));

    return weatherRecordRepository.save(weatherDataMapper.updateWeatherRecord(weatherRecord.get(),weatherData));
  }

  @Override
  public boolean checkWeatherIdExist(String weatherId) {
    return weatherRecordRepository.findById(Long.parseLong(weatherId)).isPresent();
  }
}
