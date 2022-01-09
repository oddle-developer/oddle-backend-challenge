package com.oddle.app.weather.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.dto.WeatherRequest;
import com.oddle.app.weather.dto.WeatherResponse;
import com.oddle.app.weather.entity.Weathers;
import com.oddle.app.weather.repository.WeatherRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    
    @Value("${open-api-weather.url}")
    String apiUrl;

    @Value("${open-api-weather.key}")
    String apiKey;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WeatherRepository repo;

    /*Get current weather by city name. This method will call OpenAPI Weather*/
    public String getCurrentWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String url = apiUrl.concat("?q=").concat(city).concat("&appid=").concat(apiKey);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch(ResourceAccessException e) {
            e.printStackTrace();
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
            return e.getResponseBodyAsString();
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        return null;
    }

    public WeatherResponse getWeatherResponse(String city) throws JsonProcessingException, IOException {
        String curWeather = getCurrentWeather(city);
        JsonNode node = convertStringToJsonNode(curWeather);
        WeatherResponse res = null;
        if(curWeather != null) {
            res = WeatherResponse.builder().city(city).timestamp(LocalDateTime.now()).jsonData(node).build();
        }else{
            //TODO:need to handle exception 
        }
        return res;
    }

    public WeatherResponse saveWeather(WeatherRequest req) throws JsonProcessingException, IOException {
        Weathers w = convertDtoToEntity(req);
        Weathers res = repo.save(w);
        return convertEntityToDto(res);
    }

    public String isRequestValid(String fromDt, String toDt) {
        if(fromDt == null && toDt == null) {
            return "default"; 
        } else if(fromDt == null) {
            return "fromDt cannot be empty when you set toDt";
        } else if(toDt == null) {
            return "toDt cannot be empty when you set fromDt";
        } else {
        	return "OK";
        }
    }

    public LocalDateTime parseStringDateToLocalDateTime(String strDate) throws DateTimeParseException{
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        strDate += " 00:00:00";
        return LocalDateTime.parse(strDate, formatter);
    }


    public List<WeatherResponse> getWeathersHistory(LocalDateTime fromDateTime, LocalDateTime toDateTime) throws JsonProcessingException, IOException {
        List<Weathers> wList = repo.findByTimestampBetween(fromDateTime, toDateTime);
        List<WeatherResponse> wDtos = new ArrayList<>();
        for (Weathers w : wList) {
            wDtos.add(convertEntityToDto(w));
        }
        return wDtos;
    }

    public void deleteWeather(Long id){
        repo.deleteById(id); 
    }

    public void updateWeather(Long id, WeatherRequest dto) {
        Optional<Weathers> wOpt = repo.findById(id);
        if(wOpt.isPresent()) {
            Weathers w = wOpt.get();
            w.setCity(dto.getCity());
            w.setJsonData(dto.getJsonData().toString());
            repo.save(w);
        }
    }

    public Weathers convertDtoToEntity(WeatherRequest dto){
        Weathers w = modelMapper.map(dto, Weathers.class);
        return w;
    }

    public WeatherResponse convertEntityToDto(Weathers en) throws JsonProcessingException, IOException{
        WeatherResponse wdto = modelMapper.map(en, WeatherResponse.class);
        JsonNode jsonData = convertStringToJsonNode(en.getJsonData());
        wdto.setJsonData(jsonData);
        return wdto;
    }

    public JsonNode convertStringToJsonNode(String jsonString) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonString);
        return actualObj;
    }
}
