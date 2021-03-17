package com.oddle.app.weather.feign.config;

import com.oddle.app.weather.feign.client.OpenWeatherClient;
import com.oddle.app.weather.feign.codec.BaseSvcErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Value("${open.weather.api.url}")
    private String openWeatherUrl;


    @Bean
    public OpenWeatherClient openWeatherClient() {

        return getFeignBuilder(OpenWeatherClient.class, openWeatherUrl, Logger.Level.FULL, new BaseSvcErrorDecoder(), new FormEncoder());

    }

    private <T> T getFeignBuilder(final Class<T> type, final String svcUrl, final Logger.Level level, final ErrorDecoder errorDecoder, final Encoder encoder) {

        return Feign.builder().encoder(encoder).decoder(new JacksonDecoder()).logLevel(level).errorDecoder(errorDecoder)
                 .target(type, svcUrl);
    }
}
