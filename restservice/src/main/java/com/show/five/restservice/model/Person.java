package com.show.five.restservice.model;

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
public class Person {
  private Long id;
  private String name;
  private Integer age;
  private Integer height;
  private Integer weight;
  private List<Taste> tastes;
}
