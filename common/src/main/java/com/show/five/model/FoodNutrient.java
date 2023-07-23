package com.show.five.model;

import java.math.BigDecimal;

public record FoodNutrient(Long id, Food food, Nutrient nutrient, BigDecimal value) {}
