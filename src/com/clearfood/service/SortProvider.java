package com.clearfood.service;

import com.clearfood.models.Restaurant;

import java.util.List;

public interface SortProvider {
    List<Restaurant> sort(List<Restaurant> restaurants);
}
