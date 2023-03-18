package com.oddle.app.weather.service;

import com.oddle.app.weather.client.OpenWeatherClient;
import com.oddle.app.weather.dto.GetWeatherDataRequestDto;
import com.oddle.app.weather.dto.SaveWeatherDataRequestDto;
import com.oddle.app.weather.dto.UpdateWeatherRequestDto;
import com.oddle.app.weather.dto.client.OpenWeatherGetWeatherResponseDto;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.repository.WeatherRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class WeatherServiceTest {

    @InjectMocks
    WeatherService weatherService;

    @Mock
    OpenWeatherClient openWeatherClient;

    @Mock
    WeatherRepository weatherRepository;

    @Test
    void getWeatherByCityNameTest(){
        Mockito.when(openWeatherClient.getWeatherByParams(Mockito.any())).thenReturn(Mockito.mock(OpenWeatherGetWeatherResponseDto.class));

        Assertions.assertNotNull(weatherService.getWeatherByCityName("Jakarta"));
    }

    @Test
    void saveWeatherTest(){
        SaveWeatherDataRequestDto requestDto = SaveWeatherDataRequestDto.builder()
                .cityName("Jakarta")
                .weather("Sunny")
                .temperature(100)
                .humidity(90)
                .build();

        Assertions.assertEquals(requestDto.getCityName(),weatherService.saveWeatherData(requestDto).getCityName());
    }

    @Test
    void deleteWeatherTest() throws NotFoundException {
        Weather weather = Weather.builder()
                .id(1l)
                .cityName("Bangkok")
                .weather("Rainy")
                .temperature(67)
                .humidity(23)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        UpdateWeatherRequestDto requestDto = UpdateWeatherRequestDto.builder()
                .cityName("Bangkok")
                .weather("Sunny")
                .temperature(67)
                .humidity(23)
                .build();

        Mockito.when(weatherRepository.findById(1l)).thenReturn(Optional.of(weather));

        Assertions.assertDoesNotThrow(()->weatherService.deleteWeatherById(1l));
    }

    @Test
    void getWeatherDataByDateRangeTest(){
        GetWeatherDataRequestDto requestDto = GetWeatherDataRequestDto.builder()
                .createdAtStart(LocalDateTime.of(2023, Month.MARCH, 17,00,00))
                .createdAtEnd(LocalDateTime.of(2023, Month.MARCH, 19,00,00))
                .build();

        Assertions.assertNotNull(weatherService.getWeatherData(requestDto));
    }

    @Test
    void getWeatherDataByDateRangeWithNullRequestTest(){
        GetWeatherDataRequestDto requestDto = GetWeatherDataRequestDto.builder()
                .createdAtStart(null)
                .createdAtEnd(null)
                .build();

        Assertions.assertNotNull(weatherService.getWeatherData(requestDto));
    }

    @Test
    void deleteWeatherWithDataNotFoundTest(){

        Mockito.when(weatherRepository.findById(1l)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,()->weatherService.deleteWeatherById(1l));
    }

    @Test
    void updateWeatherWithDataNotFoundTest() {
        UpdateWeatherRequestDto requestDto = UpdateWeatherRequestDto.builder()
                .cityName("Bangkok")
                .weather("Sunny")
                .temperature(67)
                .humidity(23)
                .build();

        Mockito.when(weatherRepository.findById(1l)).thenReturn(Optional.empty());


        Assertions.assertThrows(NotFoundException.class,() -> weatherService.updateWeatherById(1l,requestDto));

    }

    @Test
    void updateWeatherTest() throws NotFoundException {
        Weather weather = Weather.builder()
                .id(1l)
                .cityName("Bangkok")
                .weather("Rainy")
                .temperature(67)
                .humidity(23)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        UpdateWeatherRequestDto requestDto = UpdateWeatherRequestDto.builder()
                .cityName("Bangkok")
                .weather("Sunny")
                .temperature(67)
                .humidity(23)
                .build();

        Mockito.when(weatherRepository.findById(1l)).thenReturn(Optional.of(weather));


        Assertions.assertEquals("Sunny",weatherService.updateWeatherById(1l,requestDto).getWeather());
    }




}
