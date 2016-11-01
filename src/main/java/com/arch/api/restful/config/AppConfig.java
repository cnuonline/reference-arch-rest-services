package com.arch.api.restful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by SRINIVASULU on 31/10/16.
 */
@Configuration
//@ImportResource( { "classpath*:/rest_config.xml" } )
@ComponentScan( basePackages = "com.arch.api.restful.boot" )
@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class AppConfig{

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
