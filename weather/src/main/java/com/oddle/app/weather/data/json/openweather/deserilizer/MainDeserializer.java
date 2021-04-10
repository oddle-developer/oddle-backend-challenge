package com.oddle.app.weather.data.json.openweather.deserilizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.model.MainModel;

import java.io.IOException;

public class MainDeserializer extends StdDeserializer<MainModel> {

    static final String JSON_TEMP_PROPERTY = "temp";
    static final String JSON_TEMP_MIN_PROPERTY = "temp_min";
    static final String JSON_TEMP_MAX_PROPERTY = "temp_max";
    static final String JSON_VISIBILITY_PROPERTY = "visibility";
    static final String JSON_HUMIDITY_PROPERTY = "humidity";

    private double temp, temp_min, temp_max;

    private int visibility, humidity;

    public MainDeserializer() {
        this(null);
    }

    protected MainDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public MainModel deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        if (jsonNode.hasNonNull(JSON_TEMP_PROPERTY)) {
            temp = jsonNode.get(JSON_TEMP_PROPERTY).asDouble();
        }
        if (jsonNode.hasNonNull(JSON_TEMP_MIN_PROPERTY)) {
            temp_min = jsonNode.get(JSON_TEMP_MIN_PROPERTY).asDouble();
        }
        if (jsonNode.hasNonNull(JSON_TEMP_MAX_PROPERTY)) {
            temp_max = jsonNode.get(JSON_TEMP_MAX_PROPERTY).asDouble();
        }
        if (jsonNode.hasNonNull(JSON_VISIBILITY_PROPERTY)) {
            visibility = jsonNode.get(JSON_VISIBILITY_PROPERTY).asInt();
        }
        if (jsonNode.hasNonNull(JSON_HUMIDITY_PROPERTY)) {
            humidity = jsonNode.get(JSON_HUMIDITY_PROPERTY).asInt();
        }
        return new MainModel(
                temp, temp_min, temp_max, visibility, humidity
        );
    }
}
