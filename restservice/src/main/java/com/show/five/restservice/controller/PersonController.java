package com.show.five.restservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.show.five.model.Food;
import com.show.five.model.Person;
import com.show.five.model.Taste;
import com.show.five.restservice.remote.FoodClient;
import com.show.five.restservice.remote.FoodClient.CreateFoodData;
import com.show.five.restservice.remote.FoodClient.FoodData;
import com.show.five.restservice.remote.FoodClient.FoodsData;
import graphql.GraphqlRequest;
import graphql.GraphqlResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonController {
  private final Faker faker;
  private final FoodClient foodClient;
  private static final ObjectMapper objectMapper = new ObjectMapper();

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
    String query =
        """
        query Food($id: ID!) {
          food(id: $id) {
            id
            name
            code
            foodNutrients {
              id
              value
              nutrient {
                id
                name
                code
              }
            }
          }
        }
        """;
    final HashMap<String, Object> variables = new HashMap<>();
    variables.put("id", 2);
    final GraphqlResponse<FoodData> graphqlResponse =
        foodClient.food(new GraphqlRequest("Food", query, variables));
    if (graphqlResponse.getData() != null) {
      log.info("Food name: {}", graphqlResponse.getData().food().getName());
    }
    return people;
  }

  @GetMapping("foods")
  public List<Food> foods() {
    String query =
        """
        query {
          foods {
            id
            name
            code
            foodNutrients {
              id
              value
              nutrient {
                id
                name
                code
              }
            }
          }
        }
        """;
    final GraphqlResponse<FoodsData> graphqlResponse =
        foodClient.foods(new GraphqlRequest("Foods", query, new HashMap<>()));
    if (graphqlResponse.getData() != null) {
      return graphqlResponse.getData().foods();
    }
    return null;
  }

  @PostMapping("food")
  public Food createFood(@RequestBody Food request) {
    log.info("createFood: {}", request);
    String query =
        """
        mutation($id: ID!, $name: String!, $code: String!) {
          createFood(id: $id, name: $name, code: $code) {
            id
            name
            code
            foodNutrients {
              id
              value
              nutrient {
                id
                name
                code
              }
            }
          }
        }
        """;
    Map<String, Object> variables = new HashMap<>();
    variables.put("id", request.getId());
    variables.put("name", request.getName());
    variables.put("code", request.getCode());
    GraphqlResponse<Object> response =
        foodClient.request(new GraphqlRequest("CreateFood", query, variables));
    CreateFoodData data = objectMapper.convertValue(response.getData(), new TypeReference<>() {});
    log.info("CreateFoodData: {}", response);
    return response.getData() == null ? null : data.createFood();
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
