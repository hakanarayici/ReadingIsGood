package com.hakan.arayici.readingisgood.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi receipeApiGroup(){
        return GroupedOpenApi.builder()
                .group("Reading Is Good")
                .pathsToMatch("/**")
                .packagesToScan("com.hakan.arayici.readingisgood")
                .build();
    }

}