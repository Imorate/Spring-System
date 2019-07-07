package com.imorate.springsystem.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean("calendarConf")
    public CalenderConfiguration calenderConfiguration() {
        return new CalenderConfiguration();
    }
}