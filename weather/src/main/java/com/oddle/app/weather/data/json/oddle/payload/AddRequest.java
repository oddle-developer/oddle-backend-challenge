package com.oddle.app.weather.data.json.oddle.payload;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.oddle.deserializer.CityMapDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddRequest extends WeatherAbstractRequest {

    public static final String JSON_CITY_NAME = "name";

    public static final String JSON_CITY_LATITUDE = "latitude";

    public static final String JSON_CITY_LONGITUDE = "longitude";

    public static final String JSON_CITY = "city";

    @JsonProperty(value = JSON_CITY)
    @JsonDeserialize(using = CityMapDeserializer.class)
    private Map<String, String> city = new HashMap<>();

}
