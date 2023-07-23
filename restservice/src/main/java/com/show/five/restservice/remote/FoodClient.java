package com.show.five.restservice.remote;

import com.show.five.model.Food;
import graphql.GraphqlRequest;
import graphql.GraphqlResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("GRAPHQL-SERVICE")
public interface FoodClient {
  record FoodsData(List<Food> foods) {}

  record FoodData(Food food) {}

  @PostMapping(
      value = "/graphql",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_GRAPHQL_RESPONSE_VALUE})
  GraphqlResponse<FoodsData> foods(@RequestBody GraphqlRequest request);

  @PostMapping(
      value = "/graphql",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_GRAPHQL_RESPONSE_VALUE})
  GraphqlResponse<FoodData> food(@RequestBody GraphqlRequest request);
}
