package com.show.five.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
  private Long id;
  private String name;
  private String code;

  private List<FoodNutrient> foodNutrients;
}
