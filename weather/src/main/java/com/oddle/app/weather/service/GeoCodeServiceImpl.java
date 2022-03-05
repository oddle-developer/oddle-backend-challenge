package com.oddle.app.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oddle.app.weather.common.Constant;
import com.oddle.app.weather.dto.GeoResponseDto;
import com.oddle.app.weather.dto.OWGeocodeResponse;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class GeoCodeServiceImpl implements GeoCodeService{

    WebClient webClient = WebClient.create("https://api.openweathermap.org");

    @Override
    public GeoResponseDto getLatLonByCity(String city) throws JsonProcessingException {
        val response = webClient.get()
                .uri("/geo/1.0/direct?q="+city+"&limit=1&appid="+ Constant.API_KEY)
                .retrieve()
                .bodyToMono(OWGeocodeResponse[].class)
                .block();

        assert response != null;
        return GeoResponseDto.builder()
                .lat(Arrays.stream(response).findFirst().get().getLat())
                .lon(Arrays.stream(response).findFirst().get().getLon())
                .name(Arrays.stream(response).findFirst().get().getName())
                .build();
    }
}
