package com.oddle.app.weather.feign.client;

import com.oddle.app.weather.models.rest.WeatherData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherClient {

  @RequestLine("GET /weather?q={city}&appid={appKey}")
  @Headers("Content-Type: application/json")
  public WeatherData getWeatherByCity(@Param("city") String city, @Param("appKey") String apiKey);
}
