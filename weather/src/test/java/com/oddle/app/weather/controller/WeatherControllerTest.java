package com.oddle.app.weather.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.oddle.app.weather.dto.request.weather.save.EditWeatherDataRequest;
import com.oddle.app.weather.dto.request.weather.save.SaveWeatherDataRequest;
import com.oddle.app.weather.dto.response.HistoryWeatherResponse;
import com.oddle.app.weather.dto.response.WeatherResponse;
import com.oddle.app.weather.dto.response.openweather.OWCurrentWeatherResponse;
import com.oddle.app.weather.model.weatherdata.Weather;
import com.oddle.app.weather.service.CityWeatherService;
import com.oddle.app.weather.service.WeatherService;




@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

	
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    @Qualifier("OpenWeatherService")
    CityWeatherService cityWeatherService;
    
    @MockBean
    @Qualifier("OpenWeatherService")
    WeatherService weatherService;
    
    @Test
    public void testOpenWeatherCurrentApi() throws Exception {
    	String name = "manila";
    	OWCurrentWeatherResponse response = new OWCurrentWeatherResponse();
    	response.setName(name);
    	
		
		when(cityWeatherService.getCurrentWeather(name)).thenReturn(response);
		
        this.mockMvc.perform(get("/current").param("city", name))
        	.andExpect(jsonPath("$.data.name", is(name)))
        	.andExpect(status().isOk())
        	.andDo(print());
    }
    
    @Test
    public void testHistoryApi() throws Exception {
    	String name = "manila";
    	
    	HistoryWeatherResponse response = new HistoryWeatherResponse();
    	response.setName(name);
    	
    	List<WeatherResponse> weatherHistories = new ArrayList<>();
    	weatherHistories.add(response);
    	
    	LocalDate from = LocalDate.now();
    	LocalDate to = LocalDate.now();
    	
		when(weatherService.history(name, from, to, 0, 10)).thenReturn(new PageImpl<WeatherResponse>(weatherHistories));
		
        this.mockMvc.perform(get("/history")
        		.param("cityName", name)
        		.param("from", from.toString())
        		.param("to", to.toString())
        		.param("page", "0")
        		.param("size", "10"))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.data.content[0].name", is(name)))
        	.andDo(print());
    }
    
    @Test
    public void testSave() throws Exception {
    	String name = "manila";
    	
    	SaveWeatherDataRequest request = new SaveWeatherDataRequest();
    	request.setName(name);
    	
    	Weather weather = new Weather();
    	weather.setName(name);
    	
		when(weatherService.save(request)).thenReturn(weather);
		
	    Gson gson = new Gson();
	    String json = gson.toJson(request);
		
        this.mockMvc.perform(post("/save")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(json))
        	.andExpect(status().isOk())
        	.andDo(print());
    }
    
    @Test
    public void testSaveFieldRequired() throws Exception {
    	String name = "manila";
    	
    	SaveWeatherDataRequest request = new SaveWeatherDataRequest();
    	
    	Weather weather = new Weather();
    	weather.setName(name);
    	
		when(weatherService.save(request)).thenReturn(weather);
		
	    Gson gson = new Gson();
	    String json = gson.toJson(request);
		
        this.mockMvc.perform(post("/save")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
        	.andExpect(status().is4xxClientError())
        	.andDo(print());
    }
    
    @Test
    public void testEditFieldRequired() throws Exception {
    	String name = "manila";
    	
    	EditWeatherDataRequest request = new EditWeatherDataRequest();
    	
    	Weather weather = new Weather();
    	weather.setName(name);
    	
		when(weatherService.edit(request)).thenReturn(weather);
		
	    Gson gson = new Gson();
	    String json = gson.toJson(request);
		
        this.mockMvc.perform(post("/edit")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
        	.andExpect(status().is4xxClientError())
        	.andDo(print());
    }
    
    @Test
    public void testEdit() throws Exception {
    	String name = "manila";
    	
    	EditWeatherDataRequest request = new EditWeatherDataRequest();
    	request.setName(name);
    	
    	Weather weather = new Weather();
    	weather.setName(name);
    	
		when(weatherService.edit(request)).thenReturn(weather);
		
	    Gson gson = new Gson();
	    String json = gson.toJson(request);
		
        this.mockMvc.perform(put("/edit")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
        	.andExpect(status().isOk())
        	.andDo(print());
    }
    
    @Test
    public void testDelete() throws Exception {
    	String name = "manila";
    		
    	Weather weather = new Weather();
    	weather.setName(name);
    	
		when(weatherService.delete(1l)).thenReturn(weather);
		
		
        this.mockMvc.perform(delete("/delete/1"))
        	.andExpect(status().isOk())
        	.andDo(print());
    }
  
}
