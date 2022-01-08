package com.oddle.app.weather.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            e.getCause();
        } 
        return null;
    }

    public WeatherResponse getWeatherResponse(String city) {
        String curWeather = getCurrentWeather(city);
        WeatherResponse res = null;
        if(curWeather != null) {
            res = WeatherResponse.builder().city(city).timestamp(LocalDateTime.now()).jsonData(curWeather).build();
        }else{
            //TODO:need to handle exception 
        }
        return res;
    }

    public WeatherResponse saveWeather(WeatherRequest req) {
        Weathers w = convertDtoToEntity(req);
        Weathers res = repo.save(w);
        return convertEntityToDto(res);
    }

    public String isRequestValid(String fromDt, String toDt, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        if(fromDt == null && toDt == null) {
            return "OK"; 
        } else if(fromDt == null) {
            return "fromDt cannot be empty when you set toDt";
        } else if(toDt == null) {
            return "toDt cannot be empty when you set fromDt";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                fromDateTime = LocalDateTime.parse(fromDt, formatter);
                toDateTime = LocalDateTime.parse(toDt, formatter);
                if(fromDateTime.isBefore(toDateTime)){
                    return "OK";
                } else {
                    return "fromDt must be before toDt";
                }
            } catch (DateTimeParseException e) {
                return "Date format must use yyyy-MM-dd";
            }
        }
    }


    public List<WeatherResponse> getWeathersHistory(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
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
            w.setJsonData(dto.getJsonData());
            repo.save(w);
        }
    }

    public Weathers convertDtoToEntity(WeatherRequest dto){
        Weathers w = modelMapper.map(dto, Weathers.class);
        return w;
    }

    public WeatherResponse convertEntityToDto(Weathers en){
        WeatherResponse wdto = modelMapper.map(en, WeatherResponse.class);
        return wdto;
    }
}