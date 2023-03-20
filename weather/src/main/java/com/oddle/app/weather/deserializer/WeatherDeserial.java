package com.oddle.app.weather.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oddle.app.weather.dto.WeatherLogDto;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeatherDeserial extends StdDeserializer<WeatherLogDto> {

	private static final long serialVersionUID = 5525134549925643562L;

	protected WeatherDeserial() {
		super(WeatherLogDto.class);
	}

	@Override
	public WeatherLogDto deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

			WeatherLogDto responseDto = WeatherLogDto
				.builder()
				.date(sdf.format(new Date(node.get("dt").asLong()*1000)))
				.temporary(node.get("main").get("temp").asText())
				.temporaryMin(node.get("main").get("temp_min").asText())
				.temporaryMax(node.get("main").get("temp_max").asText())
				.city(node.get("name").asText())
				.build();
			JsonNode weathers = node.get("weather");
			if (weathers.isArray() && weathers.size() > 0){
				responseDto.setWeatherType(weathers.get(0).get("main").asText());
				responseDto.setWeatherDesc(weathers.get(0).get("description").asText());
			}
			return responseDto;
		}catch (Exception e){
			throw new RuntimeException(e.getCause());
		}
	}
}
