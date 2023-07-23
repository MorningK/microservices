package com.show.five.graphqlservice.remote;

import com.show.five.model.Person;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonClientFallback implements PersonClient {
  @Override
  public List<Person> getPersons() {
    return null;
  }
}
