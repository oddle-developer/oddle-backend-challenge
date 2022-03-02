package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.service.RestTemplateService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private RestTemplateBuilder builder;

    public RestTemplateServiceImpl(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @Override
    public RestTemplate build() {
        return builder.build();
    }
}
