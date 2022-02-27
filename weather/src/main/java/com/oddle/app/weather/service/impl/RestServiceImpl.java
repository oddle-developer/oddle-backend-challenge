package com.oddle.app.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.WeatherErrorMap;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.service.RestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestServiceImpl implements RestService {


    @Override
    public String sendMessage(String uri, Map<String, String> queryParam) throws WeatherException {

        //TODO Improve H2H request
        try {
            uri = uri + "?";
            for (String key : queryParam.keySet()) {
                uri = uri + key + "=" + queryParam.get(key) + "&";
            }
            uri = uri.substring(0,uri.length()-1);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uri, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.COMMUNICATION_FAILURE, "Failed to get data from service provider");
        }
    }
}
