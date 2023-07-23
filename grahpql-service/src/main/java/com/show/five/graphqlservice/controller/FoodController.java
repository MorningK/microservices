package com.show.five.graphqlservice.controller;

import com.github.javafaker.Faker;
import com.show.five.graphqlservice.remote.PersonClient;
import com.show.five.model.Food;
import com.show.five.model.Nutrient;
import com.show.five.model.Person;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FoodController {
  private final Faker faker;
  private final PersonClient personClient;

  @QueryMapping
  public List<Food> foods() {
    List<Food> foods = new ArrayList<>();
    for (int i = 0; i < new Random().nextInt(100); i++) {
      foods.add(buildFood());
    }
    List<Person> persons = personClient.getPersons();
    log.info("getPersons within count: {}", persons == null ? null : persons.size());
    return foods;
  }

  @QueryMapping
  public Food food(@Argument Long id) {
    if (id <= 0) {
      throw new NoSuchElementException();
    }
    final Food food = buildFood();
    food.setId(id);
    return food;
  }

  @MutationMapping
  public Food createFood(@Argument Long id, @Argument String name, @Argument String code) {
    return Food.builder().id(id).name(name).code(code).build();
  }

  private Food buildFood() {
    return Food.builder()
        .id(faker.random().nextLong())
        .name(faker.food().fruit())
        .code(faker.code().imei())
        .build();
  }

  @SchemaMapping
  public List<FoodNutrient> foodNutrients(Food food) {
    List<FoodNutrient> foodNutrients = new ArrayList<>();
    for (int i = 0; i < new Random().nextInt(40); i++) {
      foodNutrients.add(
          new FoodNutrient(
              faker.random().nextLong(),
              food,
              Nutrient.builder()
                  .id(faker.random().nextLong())
                  .name(faker.food().measurement())
                  .code(faker.code().ean8())
                  .build(),
              BigDecimal.valueOf(faker.number().randomDouble(2, 0, 100))));
    }
    return foodNutrients;
  }

  record FoodNutrient(Long id, Food food, Nutrient nutrient, BigDecimal value) {}
}
