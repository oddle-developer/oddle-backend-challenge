package com.oddle.app.weather.data.json.openweather.deserilizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.data.json.openweather.node.WindNode;

import java.io.IOException;

public class WindDeserializer extends StdDeserializer<WindNode> {

    static final String JSON_SPEED_PROPERTY = "speed";

    private double speed;

    public WindDeserializer() {
        this(null);
    }

    protected WindDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WindNode deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        WindNode windNode = new WindNode();
        JsonNode jsonNode = p.getCodec().readTree(p);
        if (jsonNode.hasNonNull(JSON_SPEED_PROPERTY)) {
            speed = jsonNode.get(JSON_SPEED_PROPERTY).asDouble();
        }
        windNode.setSpeed(speed);
        return windNode;
    }
}
