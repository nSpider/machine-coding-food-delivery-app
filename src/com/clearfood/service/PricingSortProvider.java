package com.clearfood.service;

import com.clearfood.models.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PricingSortProvider implements SortProvider{
    @Override
    public List<Restaurant> sort(List<Restaurant> restaurants) {

        return restaurants.stream()
                .sorted(Comparator.comparingInt(restaurant -> restaurant.getPrice().get()))
                .collect(Collectors.toList());

    }
}
