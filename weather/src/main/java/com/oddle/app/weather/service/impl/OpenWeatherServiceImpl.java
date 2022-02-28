package com.oddle.app.weather.service.impl;

import com.google.gson.Gson;
import com.oddle.app.weather.dto.CityDTO;
import com.oddle.app.weather.dto.openweather.ResponseOpenWeatherDTO;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.service.CityService;
import com.oddle.app.weather.service.OpenWeatherService;
import com.oddle.app.weather.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
    @Value("${openweather.apikey}")
    private String apiKey;

    @Value("${openweather.url.currentWeather}")
    private String getCurrentWeatherUrl;

    private final RestTemplateService restTemplateService;
    private final CityService cityService;

    public OpenWeatherServiceImpl(RestTemplateService restTemplateService, CityService cityService) {
        this.restTemplateService = restTemplateService;
        this.cityService = cityService;
    }

    @Override
    public ResponseOpenWeatherDTO getCurrentWeather(double lat, double lon) {
        log.info("getCurrentWeather, request current weather data lat {} and lon {}",lat, lon);
        Gson gson = new Gson();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(getCurrentWeatherUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey);

        String url = uriBuilder.build().toUriString();
        log.info("getCurrentWeather, preview request to openweather url {}", url);

        Map<String, Object> params = new LinkedHashMap<>();
        HttpEntity<?> reqEntity = new HttpEntity<>(params);
        String response = sendToOpenWeather(url, reqEntity, "POST");
        log.info("getCurrentWeather, response from openweather {}", response);

        ResponseOpenWeatherDTO responseOpenWeatherDTO =
                gson.fromJson(response, ResponseOpenWeatherDTO.class);
        log.info("getCurrentWeather, response to client {}", responseOpenWeatherDTO.toString());

        return responseOpenWeatherDTO;
    }

    @Override
    public ResponseOpenWeatherDTO getCurrentWeatherByCityName(String cityName) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(cityName);

        cityDTO = cityService.getCityByParam(cityDTO);


        return getCurrentWeather(cityDTO.getLattitude(), cityDTO.getLongitude());
    }

    private String sendToOpenWeather(String url, HttpEntity requestEntity, String httpMerhod) {
        log.info("=============================================================================");
        log.info("request to openweather api. url {}, entity {}", url, requestEntity);
        log.info("=============================================================================");
        String result = "";
        RestTemplate restTemplate = restTemplateService.build();
        restTemplate.setRequestFactory(customHttpRequestFactory(60000));

        try {
            ResponseEntity<String> rawResponse;
            if (httpMerhod.equals("GET"))
                rawResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            else if (httpMerhod.equals("DELETE"))
                rawResponse = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
            else
                rawResponse = restTemplate.postForEntity(url, requestEntity, String.class);

            log.info("=============================================================================");
            log.info("request to openweather api. response openweather {}", rawResponse.getBody());
            log.info("=============================================================================");
            if (rawResponse.getStatusCode().equals(HttpStatus.OK)
                    || rawResponse.getStatusCode().equals(HttpStatus.ACCEPTED)) {

                result = rawResponse.getBody();
            }
        }
        catch (Exception ex) {
            log.info("request to openweather api. response openweather error {}", ex.getMessage());

            throw ex;
        }

        return result;

    }

    private HttpComponentsClientHttpRequestFactory customHttpRequestFactory(int timeout) {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setReadTimeout(timeout);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(timeout);
        return httpComponentsClientHttpRequestFactory;
    }
}
