package com.show.five.restservice.mock;

import com.github.javafaker.Faker;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockConfig {

  @Bean
  public Faker faker() {
    return new Faker(Locale.CHINA);
  }
}
