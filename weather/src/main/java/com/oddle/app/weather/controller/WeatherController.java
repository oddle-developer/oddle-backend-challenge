package com.oddle.app.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.model.externalmapper.EWeatherSummary;
import com.oddle.app.weather.model.internalmapper.City;
import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import com.oddle.app.weather.service.CityService;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class WeatherController {
    private static final String API_KEY = "7c519fef05c68db1785afd811b761dd5";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CityService cityService;

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

    @PostMapping("/weather")
    public IWeatherSummary saveWeather(@RequestBody IWeatherSummary ws) {
        try {
            if (ws.getCity() != null && ws.getCity().getName() != null) {
                String cityName = ws.getCity().getName();
                String date = ws.getDate();

                if (date != null && DateUtil.isDateValid(date)) {
                    IWeatherSummary existingRecord = weatherService.getWeatherByCityAndDate(cityName, date);
                    if (existingRecord != null) {
                        return new IWeatherSummary(404, "Weather record has already exists");
                    } else {
                        City city = cityService.save(ws.getCity());
                        ws.setCity(city);

                        return weatherService.save(ws);
                    }
                } else {
                    return new IWeatherSummary(404, "Please set the date in format dd-MM-yyyy");
                }
            } else {
                return new IWeatherSummary(404, "City not found");
            }
        } catch (Exception e) {
            return new IWeatherSummary(404, e.getMessage());
        }
    }

    @PutMapping("/weather")
    public IWeatherSummary updateWeather(@RequestParam Long id, @RequestBody IWeatherSummary ws) {
        try {
            Optional<IWeatherSummary> opt = weatherService.getWeatherById(id);
            if (!opt.isPresent()) {
                return new IWeatherSummary(404, "Record does not exists");
            } else {
                IWeatherSummary existingRecord = opt.get();

                // only these four columns are update able
                existingRecord.setVisibility(ws.getVisibility());
                existingRecord.setSunrise(ws.getSunrise());
                existingRecord.setSunset(ws.getSunset());
                existingRecord.setTimezone(ws.getTimezone());

                return weatherService.save(existingRecord);
            }
        } catch (Exception e) {
            return new IWeatherSummary(404, e.getMessage());
        }
    }
}