package com.oddle.app.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:weather-integration-test.properties"
)
class WeatherApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void shouldReturnPayLoadWhenSearch() {
		try {
			mockMvc.perform(MockMvcRequestBuilders
					.get("/api/current")
					.param("city", "hanoi")
					.contentType(MediaType.TEXT_HTML))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.id").exists())
					.andExpect(jsonPath("$.weather").exists())
					.andExpect(jsonPath("$.weather.condition").exists())
					.andExpect(jsonPath("$.weather.description").exists())
					.andExpect(jsonPath("$.extend").exists())
					.andExpect(jsonPath("$.extend.temp-avg").exists())
					.andExpect(jsonPath("$.extend.temp_min").exists())
					.andExpect(jsonPath("$.extend.temp_max").exists())
					.andExpect(jsonPath("$.extend.humidity").exists())
					.andExpect(jsonPath("$.visibility").exists())
					.andExpect(jsonPath("$.wind_speed").exists())
					.andExpect(jsonPath("$.retrieval_time").exists());
		} catch (Exception exception) {
			fail();
		}
	}

	@Test
	public void shouldReturnStatus404WhenNotProvideCityWhenSearch() {
		fail();
	}

	@Test
	public void shouldReturnStatus404WhenRequestInvalidCityWhenSearch() {
		fail();
	}

	@Test
	public void shouldReturnPayloadForAGivenId() {
		fail();
	}

	@Test
	public void shouldReturnPayloadForAGivenCityAndTime() {
		fail();
	}

	@Test
	public void shouldSaveWeatherPayloadForAGivenCityAndReturnId() {
		fail();
	}

	@Test
	public void shouldReturnHistoricalWeatherPayloadForAGivenCity() {
		fail();
	}

	@Test
	public void shouldRemoveWeatherRecordForAGivenId() {
		fail();
	}

	@Test
	public void shouldUpdateWeatherRecordForAGivenId() {
		fail();
	}
}
