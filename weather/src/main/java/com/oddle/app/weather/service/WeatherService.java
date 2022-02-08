package com.oddle.app.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${api.openweathermap.url}")
    String url;

    @Value("${api.openweathermap.key}")
    String key;

    @Autowired
    WeatherRepository weatherRepository;

    /**
     * get weather from open API by city name
     */
    public WeatherResponse getWeatherByCityName(String city) {
        String data = fetchDataFromApi(city);
        WeatherResponse response = new WeatherResponse();

        /** check if data from API is empty */
        if (data == null || data.equals("")) return null;

        JsonNode node = convertStringtoJsonNode(data);
        JSONObject temp = new JSONObject(node.get("weather").get(0).toString());

        /** set city to title case */
        String newCity = Character.toTitleCase(city.charAt(0)) + city.substring(1).toLowerCase();

        /** Map to WeatherResponse Object */
        response = WeatherResponse
                .builder()
                    .id(null)
                    .city(newCity)
                    .weather(temp.get("main").toString())
                    .description(temp.get("description").toString())
                    .icon(temp.get("icon").toString())
                .build();
        return response;
    }

    /**
     * Create new weather record
     */
    public WeatherResponse createNewWeather(WeatherRequest request) {
        WeatherEntity temp = new WeatherEntity();
        BeanUtils.copyProperties(request, temp);

        /** Save data to database */
        WeatherEntity weatherEntity = weatherRepository.save(temp);

        WeatherResponse response = new WeatherResponse();
        BeanUtils.copyProperties(weatherEntity, response);

        return response;
    }

    /**
     * Get weather data from past periods
     */
    public List<WeatherResponse> getAllWeathers(int page, int limit) {
        int pageQ = (page > 0) ? (page - 1) : 0;

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

        return responseList;
    }

    /**
     * Update existing weather record
     * */
    public WeatherResponse updateWeather(Long id, WeatherRequest request){
        WeatherResponse response = new WeatherResponse();
        /** Check if data is exist */
        WeatherEntity entity = weatherRepository.findById(id).orElse(null);
        if(entity==null) return null;

        BeanUtils.copyProperties(request, entity);
        /** Update the data */
        weatherRepository.save(entity);
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    /**
     * Remove existing weather record
     * */
    public WeatherResponse removeWeather(Long id){
        WeatherResponse response = new WeatherResponse();

        /** Check if data is exist */
        WeatherEntity entity = weatherRepository.findById(id).orElse(null);
        if(entity==null) return null;

        BeanUtils.copyProperties(entity, response);
        /** Remove existing data */
        weatherRepository.delete(entity);

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
