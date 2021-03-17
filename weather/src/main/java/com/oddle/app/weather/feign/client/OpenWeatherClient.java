package com.oddle.app.weather.feign.client;

import com.oddle.app.weather.models.rest.WeatherData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url="${open.weather.api.url}",name = "Open-Weather", fallbackFactory = WeatherClientFallbackFactory.class)
public interface OpenWeatherClient {

  //@GetMapping("/weather")
  //@RequestMapping(value = "/weather", method = RequestMethod.GET)
  @RequestLine("GET /weather?q={city}&appid={appKey}")
  @Headers("Content-Type: application/json")
  public WeatherData getWeatherByCity(@Param("city") String city, @Param("appKey") String apiKey);
}
