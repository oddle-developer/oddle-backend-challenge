package com.oddle.app.weather.data;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Map<String, String> city = new HashMap<>();

    @JsonAnySetter
    public void putCityName(String key, String value) {
        putIfExist(key, value, JSON_CITY_NAME);
    }

    @JsonAnySetter
    public void putLongitude(String key, String value) {
        putIfExist(key, value, JSON_CITY_LONGITUDE);
    }

    @JsonAnySetter
    public void putLatitude(String key, String value) {
        putIfExist(key, value, JSON_CITY_LATITUDE);
    }

    private void putIfExist(String key, String value, String field) {
        if (StringUtils.hasText(key) && key.equals(field)) {
            city.put(key, value);
        }
    }

}
