package com.oddle.app.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.model.externalmapper.EWeatherSummary;
import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api")
public class WeatherController {
    private static final String API_KEY = "7c519fef05c68db1785afd811b761dd5";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }

    @GetMapping("/weather")
    public IWeatherSummary getTodaysWeather(
            @RequestParam String city,
            @RequestParam(
                    defaultValue = "#{new java.text.SimpleDateFormat(\"dd-MM-yyyy\").format(new java.util.Date())}",
                    required = false
            ) String tdyDt
    ) throws JsonProcessingException {

        // check if record already exists in the DB
        IWeatherSummary todaysWeather = weatherService.getWeatherByCityAndDate(city, tdyDt);

        // if already exists, return the object rather than calling OpenWeatherAPI
        if (todaysWeather != null) {
            return todaysWeather;
        }

        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "";

        try {
            // directly convert incoming JSON into compatible POJO (EWeatherSummary)
            ResponseEntity<EWeatherSummary> response = restTemplate.exchange(url, HttpMethod.GET, null, EWeatherSummary.class);
            EWeatherSummary weatherSummary = response.getBody();

            IWeatherSummary weather = new IWeatherSummary(weatherSummary);

            return weather;

        } catch (HttpClientErrorException e) {
            ObjectMapper mapper = new ObjectMapper();
            EWeatherSummary ews = mapper.readValue(e.getResponseBodyAsString(), EWeatherSummary.class);

            return new IWeatherSummary(ews.getCod(), ews.getMessage());
        }
    }
}