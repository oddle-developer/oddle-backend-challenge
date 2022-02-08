package com.oddle.app.weather.controller;

import com.oddle.app.weather.request.WeatherRequest;
import com.oddle.app.weather.response.CommonResponse;
import com.oddle.app.weather.response.WeatherResponse;
import com.oddle.app.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherControllerTest {

    @InjectMocks
    WeatherController controller;

    @Mock
    WeatherService weatherService;

    WeatherResponse weatherResponse;
    WeatherRequest weatherRequest;

    List<WeatherResponse> weatherResponseList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        weatherResponse = new WeatherResponse(1L, "Denpasar", "Rain", "heavy rain", "04n");
        weatherRequest = new WeatherRequest("Denpasar", "Rain", "heavy rain", "04n");

        weatherResponseList = new ArrayList<>();
        weatherResponseList.add(weatherResponse);
    }

    @Test
    @DisplayName("Get, path= /weather?city=Denmark")
    void getWeatherByCityName() {
        Mockito.when(weatherService.getWeatherByCityName(anyString())).thenReturn(weatherResponse);

        ResponseEntity<CommonResponse> test = controller.getWeatherByCityName("Denpasar");
        CommonResponse testCommonResponse = test.getBody();
        WeatherResponse testWeatherResponse = (WeatherResponse) testCommonResponse.getData();

        assertNotNull(test);
        assertEquals(weatherResponse.getCity(), testWeatherResponse.getCity());
    }

    @Test
    @DisplayName("Post, path= /weather")
    void createNewWeather() {
        Mockito.when(weatherService.createNewWeather(any(WeatherRequest.class))).thenReturn(weatherResponse);

        ResponseEntity<CommonResponse> test = controller.createNewWeather(weatherRequest);
        CommonResponse testCommonResponse = test.getBody();
        WeatherResponse testWeatherResponse = (WeatherResponse) testCommonResponse.getData();

        assertNotNull(test);
        assertEquals(weatherResponse.getCity(), testWeatherResponse.getCity());
    }

    @Test
    @DisplayName("Get, path= /weathers")
    void getAllWeathers(){
        Mockito.when(weatherService.getAllWeathers(anyInt(), anyInt())).thenReturn(weatherResponseList);

        ResponseEntity<CommonResponse> test = controller.getAllWeathers(1, 10);
        CommonResponse testCommonResponse = test.getBody();
        List<WeatherResponse> testWeatherResponseList = (List<WeatherResponse>) testCommonResponse.getData();

        assertNotNull(test);
        assertEquals(weatherResponseList.size(), testWeatherResponseList.size());
        assertEquals(weatherResponseList.get(0).getCity(), testWeatherResponseList.get(0).getCity());
    }

    @Test
    @DisplayName("Put, path= /weather/{id}")
    void updateWeather(){
        Mockito.when(weatherService.updateWeather(anyLong(), any(WeatherRequest.class))).thenReturn(weatherResponse);

        ResponseEntity<CommonResponse> test = controller.updateWeather("1", weatherRequest);
        CommonResponse testCommonResponse = test.getBody();
        WeatherResponse testWeatherResponse = (WeatherResponse) testCommonResponse.getData();

        assertNotNull(test);
        assertEquals(weatherResponse.getCity(), testWeatherResponse.getCity());
    }

    @Test
    @DisplayName("Delete, path= /weather/{id}")
    void removeWeather(){
        Mockito.when(weatherService.removeWeather(anyLong())).thenReturn(weatherResponse);

        ResponseEntity<CommonResponse> test = controller.removeWeather("1");
        CommonResponse testCommonResponse = test.getBody();
        WeatherResponse testWeatherResponse = (WeatherResponse) testCommonResponse.getData();

        assertNotNull(test);
        assertEquals(weatherResponse.getCity(), testWeatherResponse.getCity());
    }
}