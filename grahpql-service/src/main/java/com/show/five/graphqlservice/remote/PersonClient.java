package com.show.five.graphqlservice.remote;

import com.show.five.model.Person;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "persons", url = "host.docker.internal:8082")
public interface PersonClient {
  @RequestMapping(method = RequestMethod.GET, value = "persons")
  List<Person> getPersons();
}
