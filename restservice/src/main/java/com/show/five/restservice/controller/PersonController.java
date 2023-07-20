package com.show.five.restservice.controller;

import com.github.javafaker.Faker;
import com.show.five.restservice.model.Person;
import com.show.five.restservice.model.Taste;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PersonController {
  private final Faker faker;

  @PostMapping("persons")
  public List<Person> people() {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < new Random().nextInt(100); i++) {
      people.add(
          Person.builder()
              .id(faker.random().nextLong())
              .name(faker.name().username())
              .age(faker.random().nextInt(1, 100))
              .height(faker.random().nextInt(100, 200))
              .weight(faker.random().nextInt(20, 200))
              .tastes(generateTastes())
              .build());
    }
    return people;
  }

  private List<Taste> generateTastes() {
    List<Taste> tastes = new ArrayList<>();
    for (int i = 0; i < new Random().nextInt(10); i++) {
      tastes.add(
          Taste.builder().id(faker.random().nextLong(100)).name(faker.food().ingredient()).build());
    }
    return tastes;
  }
}
