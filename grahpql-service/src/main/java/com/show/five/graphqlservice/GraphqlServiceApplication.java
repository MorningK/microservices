package com.show.five.graphqlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GraphqlServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlServiceApplication.class, args);
  }
}
