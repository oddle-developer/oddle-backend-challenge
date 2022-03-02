package com.oddle.app.weather.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

@Configuration
@PropertySource("classpath:external.properties")
public class ExternalConfigUtil {

    public static String get(String key) {
        try {
            Resource resource = new ClassPathResource("/external.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
