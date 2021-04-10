package com.oddle.app.weather.data.json.oddle.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.data.json.oddle.payload.AddRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CityMapDeserializer extends StdDeserializer<Map<String, String>> {

    public CityMapDeserializer() {
        this(null);
    }

    protected CityMapDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<String, String> deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        Map<String, String> cityMap = new HashMap<>();
        JsonNode jsonNode = p.getCodec().readTree(p);
        if (jsonNode.hasNonNull(AddRequest.JSON_CITY_NAME)) {
            cityMap.put(AddRequest.JSON_CITY_NAME,  jsonNode.get(AddRequest.JSON_CITY_NAME).asText());
        }
        if (jsonNode.hasNonNull(AddRequest.JSON_CITY_LONGITUDE)) {
            cityMap.put(AddRequest.JSON_CITY_LONGITUDE, jsonNode.get(AddRequest.JSON_CITY_LONGITUDE).asText());
        }
        if (jsonNode.hasNonNull(AddRequest.JSON_CITY_LATITUDE)) {
            cityMap.put(AddRequest.JSON_CITY_LATITUDE, jsonNode.get(AddRequest.JSON_CITY_LATITUDE).asText());
        }
        return cityMap;
    }
}
