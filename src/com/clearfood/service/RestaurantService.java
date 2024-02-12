package com.clearfood.service;

import com.clearfood.models.Restaurant;
import com.clearfood.models.SortBy;

import java.util.List;

public interface RestaurantService {
     public boolean createRestaurant(Restaurant restaurant);

     public boolean updateQuantity(String restaurantName, int quantity);

     List<Restaurant> showRestaurants(SortBy sortBy, String pinCode);

     int placeOrder(String restaurantName, int quantity);
}
