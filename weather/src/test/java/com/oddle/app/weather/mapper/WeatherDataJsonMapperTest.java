package com.oddle.app.weather.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.data.json.openweather.payload.CurrentPayload;
import com.oddle.app.weather.data.mapper.OpenWeatherMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class WeatherDataJsonMapperTest {

    private static String responseBody;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void init() throws IOException {
        File json = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "dataset/current.json");
        responseBody = new String(Files.readAllBytes(json.toPath()));
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should return the current data")
    public void givenCity_whenFetchCurrentFromSite_returnWeatherData() {
        try {
            CurrentPayload payload = objectMapper.readValue(responseBody, CurrentPayload.class);
            assertNotNull(payload);
            assertNotNull(payload.getCoordNode());
            assertNotNull(payload.getWeatherNode());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
