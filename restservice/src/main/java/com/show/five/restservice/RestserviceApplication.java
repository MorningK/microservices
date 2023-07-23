package com.show.five.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestserviceApplication.class, args);
  }
}
