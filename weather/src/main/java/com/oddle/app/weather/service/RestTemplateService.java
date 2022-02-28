package com.oddle.app.weather.service;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public interface RestTemplateService {
    public RestTemplate build();
}
