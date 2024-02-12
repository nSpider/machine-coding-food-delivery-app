package com.clearfood.controller;

import com.clearfood.models.Restaurant;
import com.clearfood.models.SortBy;
import com.clearfood.models.User;
import com.clearfood.service.RestaurantService;
import com.clearfood.service.UserService;

import java.util.List;

public class UserController {

    RestaurantService restaurantService;

    UserService userService;

    User currentUser = null;

    public UserController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    //todo add login of user and set that user to current user

    public int registerUser(User user) {
        //todo add validation
        userService.registerUser(user);
        return 201;
    }

    public int loginUser(String phoneNumber) {
        currentUser = userService.loginUser(phoneNumber);

        if (currentUser == null) {
            System.out.println("Login failed: User not found");
            return 404;
        }
        System.out.println("Login success, for " + currentUser.getName());
        return 200;
    }

    public List<Restaurant> showRestaurants(SortBy sortBy) {
        return restaurantService.showRestaurants(sortBy, currentUser.getPinCode());
    }

    public int placeOrder(String restaurantName, int quantity) {
        return restaurantService.placeOrder(restaurantName, quantity);
    }
}
