package org.neidysvelasquez.claims_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.Collections;

    @Configuration
    public class CustomWebConfig {

        @Bean
        public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

            // Add support for 'application/json;charset=UTF-8'
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

            return converter;
        }

            @Bean
            public ObjectMapper objectMapper() {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new ParameterNamesModule());
                objectMapper.registerModule(new Jdk8Module());
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper;
            }
        }

