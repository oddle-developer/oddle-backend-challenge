package com.oddle.app.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.component.Utils;
import com.oddle.app.weather.entity.WeatherEntity;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.request.WeatherRequest;
import com.oddle.app.weather.response.WeatherResponse;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {

    @Value("${api.openweathermap.url}")
    String url;

    @Value("${api.openweathermap.key}")
    String key;

    @Autowired
    Utils utils;

    @Autowired
    WeatherRepository weatherRepository;

    /**
     * get weather from open API by city name
     */
    public Map<String, Object> getWeatherByCityName(String city) {
        Map<String, Object> response = new HashMap<>();
        response.put("date", utils.dateLongFormat(new Date()));

        /** check if received parameter is empty */
        if (city.equals("") || city == null) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Parameter 'city' cannot be empty!");
            return response;
        }

        String data = fetchDataFromApi(city);
        /** check if data from API is empty */
        if (data == null || data.equals("")) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "city not found");
            return response;
        }

        JsonNode node = convertStringtoJsonNode(data);
        JSONObject temp = new JSONObject(node.get("weather").get(0).toString());

        /** set city to title case */
        String newCity = Character.toTitleCase(city.charAt(0)) + city.substring(1).toLowerCase();

        /** Map to WeatherResponse Object */
        WeatherResponse weatherResponse = WeatherResponse
                .builder()
                    .id(null)
                    .city(newCity)
                    .weather(temp.get("main").toString())
                    .description(temp.get("description").toString())
                    .icon(temp.get("icon").toString())
                .build();

        response.put("data", weatherResponse);
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    /**
     * Create new weather record
     */
    public Map<String, Object> createNewWeather(WeatherRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("date", utils.dateLongFormat(new Date()));

        WeatherEntity temp = new WeatherEntity();
        BeanUtils.copyProperties(request, temp);
        WeatherEntity weatherEntity = weatherRepository.save(temp);

        WeatherResponse resObj = new WeatherResponse();
        BeanUtils.copyProperties(weatherEntity, resObj);

        response.put("data", resObj);
        response.put("message", "data successfully created!");
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    /**
     * Get weather data from past periods
     */
    public Map<String, Object> getAllWeathers(int page, int limit) {
        int pageQ = (page > 0) ? (page - 1) : 0;

        Map<String, Object> response = new HashMap<>();
        response.put("date", utils.dateLongFormat(new Date()));

        List<WeatherResponse> responseList = new ArrayList<>();

        /** To store data to list as per page and per limit */
        Pageable pageableRequest = PageRequest.of(pageQ, limit);
        Page<WeatherEntity> weatherEntityPage = weatherRepository.findAll(pageableRequest);
        List<WeatherEntity> weatherEntityList = weatherEntityPage.getContent();

        /** store data to response list */
        for (WeatherEntity w : weatherEntityList) {
            WeatherResponse wr = new WeatherResponse();
            BeanUtils.copyProperties(w, wr);
            responseList.add(wr);
        }

        response.put("data", responseList);
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    /**
     * Update existing weather record
     * */
    public Map<String, Object> updateWeather(Long id, WeatherRequest request){
        Map<String, Object> response = new HashMap<>();
        response.put("date", utils.dateLongFormat(new Date()));

        /** Check if data is exist */
        WeatherEntity entity = weatherRepository.findById(id).orElse(null);
        if(entity==null){
            response.put("message", "Record not found!");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return response;
        }

        /** Update the data */
        BeanUtils.copyProperties(request, entity);

        weatherRepository.save(entity);

        WeatherResponse responseObj = new WeatherResponse();
        BeanUtils.copyProperties(entity, responseObj);

        response.put("data", responseObj);
        response.put("message", "data successfully updated!");
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    /**
     * Remove existing weather record
     * */
    public Map<String, Object> removeWeather(Long id){
        Map<String, Object> response = new HashMap<>();
        response.put("date", utils.dateLongFormat(new Date()));

        /** Check if data is exist */
        WeatherEntity entity = weatherRepository.findById(id).orElse(null);
        if(entity==null){
            response.put("message", "Record not found!");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return response;
        }

        weatherRepository.delete(entity);

        response.put("message", "data successfully removed!");
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    /**
     * This method used to fetch data from open API
     */
    private String fetchDataFromApi(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String response = null;

        try {
            String buildURL = url.concat("?q=").concat(city).concat("&appid=").concat(key);
            response = restTemplate.getForObject(buildURL, String.class);
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Converting string of data to a json node
     */
    private JsonNode convertStringtoJsonNode(String str) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = null;
        try {
            data = mapper.readTree(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }
}
