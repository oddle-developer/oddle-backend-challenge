package com.oddle.app.weather.data.json.openweather.deserilizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.model.WeatherModel;

import java.io.IOException;

public class WeatherDeserializer extends StdDeserializer<WeatherModel> {

    static final String JSON_MAIN_PROPERTY = "main";
    static final String JSON_DESCRIPTION_PROPERTY = "description";

    private String main, description;

    public WeatherDeserializer() {
        this(null);
    }

    public WeatherDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WeatherModel deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        if (jsonNode.hasNonNull(JSON_MAIN_PROPERTY)) {
            main = jsonNode.get(JSON_MAIN_PROPERTY).asText();
        }
        if (jsonNode.hasNonNull(JSON_DESCRIPTION_PROPERTY)) {
            description = jsonNode.get(JSON_DESCRIPTION_PROPERTY).asText();
        }
        return new WeatherModel(main, description);
    }
}
