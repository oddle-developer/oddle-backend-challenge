package com.oddle.app.weather.data.json.openweather.deserilizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.data.json.openweather.node.CoordNode;

import java.io.IOException;

public class CoordDeserializer extends StdDeserializer<CoordNode> {

    static final String JSON_LON_PROPERTY = "lon";
    static final String JSON_LAT_PROPERTY = "lat";

    private double lon, len;

    public CoordDeserializer() {
        this(null);
    }

    protected CoordDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CoordNode deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        if (jsonNode.hasNonNull(JSON_LON_PROPERTY)) {
            lon = jsonNode.get(JSON_LON_PROPERTY).asDouble();
        }
        if (jsonNode.hasNonNull(JSON_LAT_PROPERTY)) {
            len = jsonNode.get(JSON_LAT_PROPERTY).asDouble();
        }
        return new CoordNode(lon, len);
    }
}
