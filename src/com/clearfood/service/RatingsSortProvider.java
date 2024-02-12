package com.clearfood.service;

import com.clearfood.models.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RatingsSortProvider implements  SortProvider{
    @Override
    public List<Restaurant> sort(List<Restaurant> restaurants) {

        return restaurants.stream()
                .sorted((restaurant1, restaurant2) -> (int) (restaurant2.getRating() - restaurant1.getRating()))
                .collect(Collectors.toList());
    }
}
