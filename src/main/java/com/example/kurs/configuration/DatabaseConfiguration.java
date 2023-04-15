package com.example.kurs.configuration;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategyStandardImpl() {
        return new PhysicalNamingStrategyStandardImpl();
    }
}
