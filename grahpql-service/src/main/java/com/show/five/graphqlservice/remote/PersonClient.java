package com.show.five.graphqlservice.remote;

import com.show.five.model.Person;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "REST-SERVICE", fallback = PersonClientFallback.class)
public interface PersonClient {
  @GetMapping("persons")
  List<Person> getPersons();
}
