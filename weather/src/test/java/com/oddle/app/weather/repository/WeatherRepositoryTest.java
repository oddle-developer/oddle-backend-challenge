package com.oddle.app.weather.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oddle.app.weather.model.weatherdata.Weather;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = "tests")
public class WeatherRepositoryTest {
	
	@Autowired
	WeatherDataRepository weatherDataRepository;
	
	@Test
	void injectedComponentsAreNotNull(){
		Weather weather = new Weather();
		weather.setId(1l);
		weather.setName("cloudy");
		weather.setActive(true);
		
		weatherDataRepository.save(weather);
		
		Weather weatherResponse = weatherDataRepository.findByIdAndIsActive(1l, true).get();
			  
		assertThat(weatherResponse.getName()).isEqualTo("cloudy");
	}
}
