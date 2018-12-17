package com.example.app.application.services;

import com.example.app.domain.services.DomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

  @Bean
  DomainService domainService() {
    return new DomainService();
  }
}
