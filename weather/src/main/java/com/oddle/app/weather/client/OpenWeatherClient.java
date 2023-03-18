package com.oddle.app.weather.client;

import com.oddle.app.weather.dto.client.OpenWeatherGetWeatherResponseDto;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.stream.Collectors.joining;

@Component
public class OpenWeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    //These variables value are declared in the application properties
    //Keep the value updated if there are any changes
    @Value("${open-weather.base-url}")
    private String openWeatherBaseUrl;

    @Value("${open-weather.version-url-path}")
    private String openWeatherVersionPath;

    @Value("${open-weather.weather-url-path}")
    private String openWeatherWeatherPath;

    @Value("${open-weather.api-key}")
    private String openWeatherApiKey;

    private final String API_KEY_PARAM_NAME = "appid";

    public OpenWeatherGetWeatherResponseDto getWeatherByParams(Map<String, String> requestParams){

        addApiKeyParams(requestParams);

        String queryURL = requestParams.keySet().stream()
                .map(key -> key + "=" + requestParams.get(key))
                .collect(joining("&", openWeatherBaseUrl + openWeatherVersionPath + openWeatherWeatherPath + "?", ""));

        ParameterizedTypeReference<OpenWeatherGetWeatherResponseDto> responseType = new ParameterizedTypeReference<OpenWeatherGetWeatherResponseDto>() {};

        ResponseEntity<OpenWeatherGetWeatherResponseDto> response = restTemplate.exchange(
                queryURL,
                HttpMethod.GET,
                null,
                OpenWeatherGetWeatherResponseDto.class
        );

        return response.getBody();
    }

    public void addApiKeyParams(Map<String, String> request){
        request.put(API_KEY_PARAM_NAME, openWeatherApiKey);
    }

}
