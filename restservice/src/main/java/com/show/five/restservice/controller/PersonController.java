package com.show.five.restservice.controller;

import com.github.javafaker.Faker;
import com.show.five.model.Person;
import com.show.five.model.Taste;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonController {
  private final Faker faker;

  @GetMapping("persons")
  public List<Person> people() {
    List<Person> people = new ArrayList<>();
    int count = new Random().nextInt(100);
    for (int i = 0; i < count; i++) {
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
    log.info("find person with count: {}", count);
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
