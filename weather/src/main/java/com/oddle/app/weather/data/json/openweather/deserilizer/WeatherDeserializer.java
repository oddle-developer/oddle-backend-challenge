package com.oddle.app.weather.data.json.openweather.deserilizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.oddle.app.weather.data.json.openweather.node.WeatherNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherDeserializer extends StdDeserializer<List<WeatherNode>> {

    static final String JSON_MAIN_PROPERTY = "main";
    static final String JSON_DESCRIPTION_PROPERTY = "description";

    private List<WeatherNode> weatherNodes;

    public WeatherDeserializer() {
        this(null);
        weatherNodes = new ArrayList<>();
    }

    public WeatherDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<WeatherNode> deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        ArrayNode jsonNode = p.getCodec().readTree(p);
        Iterator<JsonNode> nodeIterator = jsonNode.elements();
        while (nodeIterator.hasNext()) {
            String main = null, description = null;
            JsonNode currentNode = nodeIterator.next();
            if (currentNode.hasNonNull(JSON_MAIN_PROPERTY)) {
                main = currentNode.get(JSON_MAIN_PROPERTY).asText();
            }
            if (currentNode.hasNonNull(JSON_DESCRIPTION_PROPERTY)) {
                description = currentNode.get(JSON_DESCRIPTION_PROPERTY).asText();
            }
            weatherNodes.add(new WeatherNode(main, description));
        }

        return weatherNodes;
    }
}
