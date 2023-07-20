package com.show.five.graphqlservice.controller;

import com.github.javafaker.Faker;
import com.show.five.graphqlservice.model.Food;
import com.show.five.graphqlservice.model.Nutrient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class FoodController {
  private final Faker faker;

  @QueryMapping
  public List<Food> foods() {
    List<Food> foods = new ArrayList<>();
    for (int i = 0; i < new Random().nextInt(100); i++) {
      foods.add(
          Food.builder()
              .id(faker.random().nextLong())
              .name(faker.food().fruit())
              .code(faker.code().imei())
              .build());
    }
    return foods;
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
